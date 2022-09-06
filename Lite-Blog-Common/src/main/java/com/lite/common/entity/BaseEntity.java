package com.lite.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsExclude;

import java.time.LocalDateTime;

/**
 * 基础实体，带有逻辑删除和乐观锁机制
 * @author Stranger
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BaseEntity extends Entity {

    @Version
    private Integer version;

    private Boolean deleted;

    public BaseEntity(){
        this.setCreatedTime(LocalDateTime.now());
        this.setUpdatedTime(LocalDateTime.now());
        this.deleted = false;
        this.version = 0;
    }

    public void update(){
        this.setUpdatedTime(LocalDateTime.now());
    }
}
