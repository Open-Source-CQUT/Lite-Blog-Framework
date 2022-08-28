package com.lite.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lite.system.entity.SystemController;

import java.util.List;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/26 23:30
 */

public interface SystemCtrlService extends IService<SystemController> {

    public Integer updateCtrlList(List<SystemController> list);
}
