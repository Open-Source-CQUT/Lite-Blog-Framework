package com.lite.auth.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTokenVo {

    private Long id;

    private String mail;

    private String nickName;

    private String avatar;

    private String gender;

    private Integer permissionId;

    private String loginTime;

    private String uuid;

    private String job;

    private String company;

}
