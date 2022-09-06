package com.lite.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lite.system.dao.SystemApiMapper;
import com.lite.system.entity.SystemApi;
import com.lite.system.service.SystemApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 10:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SystemApiServiceImpl extends ServiceImpl<SystemApiMapper, SystemApi> implements SystemApiService {

    @Autowired
    SystemApiMapper apiMapper;


    public Integer updateApiList(List<SystemApi> list){
        return apiMapper.updateList(list);
    }
}
