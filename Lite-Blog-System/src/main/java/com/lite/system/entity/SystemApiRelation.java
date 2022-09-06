package com.lite.system.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.lite.common.entity.BaseEntity;
import com.lite.common.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/27 17:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@TableName("relation_sys_api_controller")
public class SystemApiRelation extends Entity {

    Long controllerId;

    Long apiId;

    public SystemApiRelation(Long controllerId, Long apiId) {
        this.controllerId = controllerId;
        this.apiId = apiId;
    }
}
