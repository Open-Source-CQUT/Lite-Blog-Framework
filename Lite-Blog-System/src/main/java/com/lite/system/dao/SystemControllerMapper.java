package com.lite.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lite.system.entity.SystemController;

import java.util.List;

public interface SystemControllerMapper extends BaseMapper<SystemController> {

   Integer insertControllerList(List<SystemController> list);

   Integer updateControllerList(List<SystemController> list);
}
