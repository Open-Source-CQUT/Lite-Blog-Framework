package com.lite.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.Data;

@Data
@TableName("info_sys_permission")
public class SystemPermission extends BaseEntity {

    private Integer code;

    private String description;

}
