package com.lite.auth.config.mp;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lite.common.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    AutoInjectConfig autoInjectConfig;

    @Override
    public void insertFill(MetaObject metaObject) {
        autoInjectConfig.getTimeInsert().forEach(field-> this.setFieldValByName(field, DateUtils.formatNow(),metaObject));
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        autoInjectConfig.getTimeUpdate().forEach(field->this.setFieldValByName(field,DateUtils.formatNow(),metaObject));
    }

}
