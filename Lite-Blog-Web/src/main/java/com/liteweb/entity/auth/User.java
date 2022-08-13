package com.liteweb.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.sql.Date;

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