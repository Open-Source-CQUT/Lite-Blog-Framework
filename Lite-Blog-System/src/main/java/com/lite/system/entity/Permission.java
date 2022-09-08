package com.lite.system.entity;

import com.lite.common.entity.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/7 22:34
 */

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
public class Permission extends Entity {

    private Integer code;

    private String description;

}
