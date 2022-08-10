package com.liteweb.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String mail;

    private String avatar;

    private String nickName;

    private Integer gender;

    private String description;

    private Date createdTime;

    private Date updatedTime;

    private Integer roleId;

}
