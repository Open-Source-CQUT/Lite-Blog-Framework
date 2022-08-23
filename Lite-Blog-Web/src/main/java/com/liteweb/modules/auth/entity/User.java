package com.liteweb.modules.auth.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("info_user")
public class User {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String mail;

    private String password;

    private String nickName;

    private String description;

    private String avatar;

    private String birth;

    private Integer gender;

    private Integer roleId;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String createdTime;

    @TableField(fill = FieldFill.UPDATE)
    private String updatedTime;

    private Boolean deleted;

}
