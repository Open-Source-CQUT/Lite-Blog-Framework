package com.lite.system.dao;

import com.lite.common.entity.Entity;
import com.lite.system.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    void insertPermissionData(@Param("permissionList") List<Permission> permissionList, @Param("table") String table);
}
