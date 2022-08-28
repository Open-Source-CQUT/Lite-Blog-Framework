package com.lite.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNormalDto {

    private String mail;

    private String password;

    private String nickName;

    private String authCode;

    private String description;

    private String avatar;

    private String birth;

    private Integer gender;

    private Integer permissionId;

    private String job;

    private String company;
}
