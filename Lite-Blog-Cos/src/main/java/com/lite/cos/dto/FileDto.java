package com.lite.cos.dto;

import lombok.Data;

@Data
public class FileDto {

    private String fileName;

    private String originalName;

    private String url;

    private String type;

    private String uploader;

    private String uploadTime;

}
