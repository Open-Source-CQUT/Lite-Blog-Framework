package com.lite.common.entity;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

/**
 * @author Stranger
 * @version 1.0
 * @description: 查询条件包装抽象类，多用于实体类多条件查询
 * @date 2022/10/25 22:08
 */
public abstract class QueryConditionWrapper<T> {

    public abstract LambdaQueryWrapper<T> buildWrapper();
}
