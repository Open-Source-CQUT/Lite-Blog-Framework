package com.lite.system.entity;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 21:57
 */
public enum PermissionId {

    DEFAULT(0),
    USER(0),
    ADMIN(1),
    SUPER_ADMIN(2);

    final int id;
    PermissionId(int id) {
        this.id = id;
    }

    public int val() {
        return id;
    }

}
