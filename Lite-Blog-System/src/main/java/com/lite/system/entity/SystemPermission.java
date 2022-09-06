package com.lite.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Stranger
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("info_sys_permission")
public class SystemPermission extends BaseEntity {

    private Integer code;

    private String description;

}
