package com.lite.system.entity;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 21:57
 */
public enum PermissionId {


    USER(0, "普通用户"),
    ADMIN(1, "管理员"),
    SUPER_ADMIN(2, "超级管理员");

    final int id;

    final String msg;

    PermissionId(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }

    public int val() {
        return id;
    }

    public String msg() {
        return this.msg;
    }

}
