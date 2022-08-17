package com.liteweb.modules.auth.dto.user;

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

    private String description;

    private String avatar;

    private String birth;

    private Integer gender;

    private Integer roleId;
}
