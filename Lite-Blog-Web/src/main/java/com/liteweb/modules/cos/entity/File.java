package com.liteweb.modules.cos.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("info_file")
public class File {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String fileName;

    private String originalName;

    private String url;

    private String type;

    private Boolean access;

    private String bucket;

    private Long uploader;

    @TableField(fill = FieldFill.INSERT)
    private String uploadTime;

    private Boolean deleted;

}
