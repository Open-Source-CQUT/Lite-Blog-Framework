package com.lite.api.controller.publics.mail;


import com.lite.common.dto.ResultResponse;
import com.lite.common.i18n.SystemMessages;
import com.lite.common.utils.ResultResponseUtils;
import com.lite.mail.Vo.MailVo;
import com.lite.mail.exception.MailException;
import com.lite.mail.service.MailService;
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
        return mailService.sendSimpleMail(mailVo) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.mail.send")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.mail.send"));
    }

    @PostMapping("/sendHtmlMail")
    public ResultResponse<Boolean> sendHtmlMail(@RequestBody @Validated MailVo mailVo) throws MessagingException {
        return mailService.sendHtmlMail(mailVo) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.mail.send")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.mail.send"));
    }

    @PostMapping("/sendAuthMail")
    public ResultResponse<Boolean> sendAuthMail(@RequestParam @NotBlank String target) throws MessagingException, MailException {
        return mailService.sendAuthMail(target) ?
                ResultResponseUtils.success(true, SystemMessages.get("success.mail.send")) :
                ResultResponseUtils.error(false, SystemMessages.get("error.mail.send"));
    }

}
