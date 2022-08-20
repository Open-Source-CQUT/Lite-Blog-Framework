package com.liteweb.modules.auth.vo;

import com.liteweb.modules.common.validation.groups.NormalGroups;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    @NotBlank(message = "用户邮箱不能为空", groups = {NormalGroups.Crud.Insert.class, NormalGroups.Crud.Query.class})
    @Email(message = "错误的邮箱格式", groups = {NormalGroups.Crud.Insert.class, NormalGroups.Crud.Query.class})
    private String mail;

    @NotBlank(message = "用户密码不能为空", groups = {NormalGroups.Crud.Insert.class, NormalGroups.Crud.Query.class})
    private String password;

    @NotBlank(message = "用户昵称不能为空", groups = {NormalGroups.Crud.Insert.class})
    private String nickName;

    private String description;

    private String birth;

    private String avatar;

    @NotBlank(message = "性别不能为空", groups = {NormalGroups.Crud.Insert.class})
    private String gender;

    private Integer roleId;

}
