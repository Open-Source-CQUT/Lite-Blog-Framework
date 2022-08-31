package com.lite.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.lite.common.entity.BaseEntity;
import lombok.*;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@TableName("info_user")
public class User extends BaseEntity {

    private String mail;

    private String password;

    private String nickName;

    private String description;

    private String avatar;

    private String birth;

    private Integer gender;

    private Integer permissionId;

    private String job;

    private String company;

}
