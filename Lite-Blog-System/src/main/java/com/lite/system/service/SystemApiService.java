package com.lite.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lite.system.entity.SystemApi;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 10:27
 */
public interface SystemApiService extends IService<SystemApi> {

    public Integer updateApiList(List<SystemApi> list);
}
