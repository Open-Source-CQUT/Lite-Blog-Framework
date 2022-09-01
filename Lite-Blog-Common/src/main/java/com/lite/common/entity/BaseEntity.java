package com.lite.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @Version
    private Integer version;

    private Boolean deleted;

    public BaseEntity(){
        this.createdTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        this.deleted = false;
        this.version = 0;
    }

    public void update(){
        this.updatedTime = LocalDateTime.now();
    }
}
