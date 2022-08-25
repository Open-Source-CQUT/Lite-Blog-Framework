package com.lite.mail.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailVo {

    @NotBlank(message = "邮箱发送目标不能为空")
    @Email(message = "非法的邮箱")
    private String target;

    @NotBlank(message = "邮箱主题不能为空")
    private String subject;

    @NotBlank(message = "邮箱内容不能为空")
    private String content;
}
