package com.lite.system.entity;

import com.lite.common.entity.BaseEntity;
import com.lite.common.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/8/26 21:50
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class SystemEntity extends Entity {

    /**
     * 映射路径
     */
    private String url;

    /**
     * 简单名称
     */
    private String simpleName;

    /**
     * 完整限定名称
     */
    private String fullName;

}
