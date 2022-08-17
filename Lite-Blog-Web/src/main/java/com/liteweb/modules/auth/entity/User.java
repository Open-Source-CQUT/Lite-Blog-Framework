package com.liteweb.modules.auth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String mail;

    private String password;

    private String nickName;

    private String description;

    private String avatar;

    private String birth;

    private Integer gender;

    private Integer roleId;

    private String createdTime;

    private String updatedTime;

    private Integer deleted;

}
