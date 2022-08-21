package com.liteweb.modules.cos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {

    private String fileName;

    private String originalName;

    private String url;

    private String type;

    private Boolean access;

    private String bucket;

    private String uploader;

    private String uploadTime;

    private Boolean deleted;

}
