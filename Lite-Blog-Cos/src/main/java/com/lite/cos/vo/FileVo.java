package com.lite.cos.vo;

import lombok.Data;

@Data
public class FileVo {

    private String fileName;

    private String originalName;

    private String url;

    private String type;

    private Boolean access;

    private String uploader;
}
