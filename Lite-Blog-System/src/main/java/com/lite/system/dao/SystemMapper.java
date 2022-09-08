package com.lite.system.dao;

import com.lite.common.entity.Entity;
import org.apache.ibatis.annotations.Param;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/7 20:46
 */

public interface SystemMapper {

    void createPermissionTable(@Param("table") String table);

    void createUserTable(@Param("table") String table);

    void createFileTable(@Param("table") String table);

    void createApiTable(@Param("table") String table);

    void createControllerTable(@Param("table") String table);

    void createApiCtrlTable(@Param("table") String table);
}
