package com.lite.cos.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lite.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("info_file")
public class File extends BaseEntity {

    private String fileName;

    private String originalName;

    private String url;

    private String type;

    private Boolean access;

    private String bucket;

    private Long uploader;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime uploadTime;

}
