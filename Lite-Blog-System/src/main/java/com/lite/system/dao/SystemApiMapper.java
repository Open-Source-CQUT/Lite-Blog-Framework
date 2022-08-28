package com.lite.system.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lite.system.entity.SystemApi;

import java.util.List;

public interface SystemApiMapper extends BaseMapper<SystemApi> {

    Integer insertApiList(List<SystemApi> list);

    Integer updateList(List<SystemApi> list);

}
