package com.lite.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lite.system.dao.SystemControllerMapper;
import com.lite.system.entity.SystemController;
import com.lite.system.service.SystemCtrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 10:24
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class SystemCtrlServiceImpl extends ServiceImpl<SystemControllerMapper, SystemController> implements SystemCtrlService {

    @Autowired
    SystemControllerMapper systemControllerMapper;


    public Integer updateCtrlList(List<SystemController> list){
        return systemControllerMapper.updateControllerList(list);
    }
}
