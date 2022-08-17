package com.liteweb.modules.auth.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenVo {

    private String mail;

    private String nickName;

    private String avatar;

    private String gender;

    private Integer roleId;

    private String loginTime;

    private String uuid;

}
