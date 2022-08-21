package com.liteweb.modules.mail.controller;

import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.mail.Vo.MailVo;
import com.liteweb.modules.mail.exception.MailException;
import com.liteweb.modules.mail.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/mail")
@Validated
public class MailController {

    @Autowired
    MailService mailService;

    @PostMapping("/sendSimpleMail")
    public ResultResponse<Boolean> sendSimpleTextMail(@RequestBody @Validated MailVo mailVo) {
        return mailService.sendSimpleMail(mailVo);
    }

    @PostMapping("/sendHtmlMail")
    public ResultResponse<Boolean> sendHtmlMail(@RequestBody @Validated MailVo mailVo) throws MessagingException {
        return mailService.sendHtmlMail(mailVo);
    }

    @PostMapping("/sendAuthMail")
    public ResultResponse<Boolean> sendAuthMail(@RequestParam @NotBlank String target) throws MessagingException, MailException {
        return mailService.sendAuthMail(target);
    }

}
