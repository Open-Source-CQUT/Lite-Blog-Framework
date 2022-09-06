package com.lite.system.core.iml;

import com.lite.common.i18n.SystemMessages;
import com.lite.common.serializer.RedisCache;
import com.lite.system.config.SystemConfig;
import com.lite.system.entity.SystemApi;
import com.lite.system.entity.SystemApiRelation;
import com.lite.system.entity.SystemController;
import com.lite.system.entity.SystemEntity;
import com.lite.system.service.SystemApiRelationService;
import com.lite.system.service.SystemApiService;
import com.lite.system.service.SystemCtrlService;
import com.lite.system.utils.list.ListUtils;
import com.lite.system.utils.list.iml.SystemEntityComparator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 14:34
 */
@Slf4j
@Component
public class DefaultSystemManager extends AbstractSystemManager {

    @Autowired
    SystemApiService apiService;

    @Autowired
    SystemCtrlService ctrlService;

    @Autowired
    SystemApiRelationService relationService;

    @Autowired
    SystemEntityComparator comparator;

    @Autowired
    RedisCache redisCache;

    @Autowired
    SystemConfig systemConfig;


    @Override
    public void loadSystemInfo(WebApplicationContext applicationContext) {

        //刷新获取容器信息
        refreshContextInfo(applicationContext);

        //处理controller信息
        processSystemControllerInfo();

        //处理api信息
        processSystemApiInfo();

        processRelationInfo();
    }

    /**
     * 进行controller信息处理
     */
    public void processSystemControllerInfo() {

        //数据库原始信息
        List<SystemController> originalControllerList = ctrlService.list();

        //系统加载的信息
        List<SystemController> systemControllerList = loadSystemControllerInfo();

        if (systemConfig.isLogEnable())
        //日志输出
        {
            log.info(SystemMessages.get("success.sys.ctrl.load", systemControllerList.size()));
        }

        //进行信息比对
        judgeSystemController(systemControllerList, originalControllerList);

        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("success.sys.ctrl.map"));
            log.info(SystemMessages.get("arrowhead"));
        }

    }

    /**
     * 进行Api信息处理
     */
    public void processSystemApiInfo() {

        //数据库原始信息
        List<SystemApi> originalApiList = apiService.list();

        //系统加载出来的信息
        List<SystemApi> systemApis = loadSystemApiInfo();

        if (systemConfig.isLogEnable())
        //日志输出
        {
            log.info(SystemMessages.get("success.sys.api.load", systemApis.size()));
        }

        //进行信息比对
        judgeSystemApi(systemApis, originalApiList);

        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("success.sys.api.map"));
            log.info(SystemMessages.get("arrowhead"));
        }
    }

    public void processRelationInfo() {

        //读取数据库原始信息
        List<SystemApiRelation> relationList = relationService.list();

        //更新后的新信息
        List<SystemController> ctrlList = ctrlService.list();

        //更新后的信息
        List<SystemApi> apiList = apiService.list();

        //组装
        List<SystemApiRelation> wrapRelationList = wrapRelation(ctrlList, new ArrayList<>(apiList));

        //进行信息比对
        judgeSystemApiRelation(wrapRelationList, relationList);

        //组装成map
        Map<String, SystemApi> systemApiMap = apiList.stream().collect(Collectors.toMap(SystemEntity::getUrl, api -> api));

        //先删除
        redisCache.deleteObject(systemConfig.getRedisMapKey());
        //存入redis中
        redisCache.setCacheMap(systemConfig.getRedisMapKey(), systemApiMap);

        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("success.sys.api.redis"));
        }
    }

    public List<SystemApiRelation> wrapRelation(List<SystemController> systemControllerList, List<SystemApi> apiList) {

        List<SystemApiRelation> relationList = new ArrayList<>();

        systemControllerList.forEach(ctrl -> {
            //若api的全限定名称包含ctrl的全限定名称，则该api一定属于ctrl
            List<SystemApi> belongList = apiList
                    .stream()
                    .filter(api -> api.getFullName().contains(ctrl.getFullName()))
                    .collect(Collectors.toList());

            belongList.forEach(api -> relationList.add(new SystemApiRelation(ctrl.getId(), api.getId())));

            //api只能同时属于一个ctrl,为了省去不必要的遍历,所以可以将其删除
            apiList.removeAll(belongList);
        });

        return relationList;
    }


    /**
     * 将系统的Controller进行比对，然后进行相应的数据库操作
     *
     * @param systemList   系统加载出来的Controller
     * @param databaseList 数据库所存储的Controller
     */
    @Transactional(rollbackFor = Exception.class)
    public void judgeSystemController(List<SystemController> systemList, List<SystemController> databaseList) {

        //系统相较于数据库多出来的接口
        List<SystemController> extraList = ListUtils.castList(
                comparator.findExtraPart(systemList, databaseList), SystemController.class);

        //系统相较于数据库缺少的接口
        List<SystemController> lessList = ListUtils.castList(
                comparator.findLessPart(systemList, databaseList), SystemController.class);

        //系统相较于数据库信息更新过的接口
        List<SystemController> updatedList = ListUtils.castList(
                comparator.findUpdatePart(systemList, databaseList), SystemController.class);

        if (!extraList.isEmpty()) {
            ctrlService.saveBatch(extraList);
        }

        if (!lessList.isEmpty()) {
            ctrlService.removeBatchByIds(lessList, true);
        }

        if (!updatedList.isEmpty()) {
            ctrlService.updateCtrlList(updatedList);
        }

        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("success.sys.ctrl.add", extraList.size()));
            log.info(SystemMessages.get("success.sys.ctrl.less", lessList.size()));
            log.info(SystemMessages.get("success.sys.ctrl.update", updatedList.size()));
        }
    }

    /**
     * 将系统的API信息进行比对，然后进行相应的数据库操作
     *
     * @param systemApis   系统加载出的API
     * @param databaseApis 数据库所存储的API
     */
    @Transactional(rollbackFor = Exception.class)
    public void judgeSystemApi(List<SystemApi> systemApis, List<SystemApi> databaseApis) {

        List<SystemApi> extraList = ListUtils.castList(
                comparator.findExtraPart(systemApis, databaseApis), SystemApi.class);

        List<SystemApi> lessList = ListUtils.castList(
                comparator.findLessPart(systemApis, databaseApis), SystemApi.class);

        List<SystemApi> updatedList = ListUtils.castList(
                comparator.findUpdatePart(systemApis, databaseApis), SystemApi.class);

        if (!extraList.isEmpty()) {
            apiService.saveBatch(extraList);
        }

        //进行delete删除，而非逻辑删除
        if (!lessList.isEmpty()) {
            apiService.removeBatchByIds(lessList, true);
        }

        if (!updatedList.isEmpty()) {
            apiService.updateApiList(updatedList);
        }

        if (systemConfig.isLogEnable()) {
            log.info(SystemMessages.get("success.sys.api.add", extraList.size()));
            log.info(SystemMessages.get("success.sys.api.less", lessList.size()));
            log.info(SystemMessages.get("success.sys.api.update", updatedList.size()));
        }

    }

    public void judgeSystemApiRelation(List<SystemApiRelation> systemRelationList, List<SystemApiRelation> databaseRelationList) {

        //创建空白的操作列表,并找出系统相对于数据增加的API关系以及删减的关系
        List<SystemApiRelation> moreList = new ArrayList<>(systemRelationList);
        moreList.removeAll(databaseRelationList);

        List<SystemApiRelation> lessList = new ArrayList<>(databaseRelationList);
        lessList.removeAll(systemRelationList);

        //进行数据库操作
        if (!moreList.isEmpty()){
            relationService.saveBatch(moreList);
        }

        if (!lessList.isEmpty()){
            relationService.removeBatchByIds(lessList);
        }

    }
}
