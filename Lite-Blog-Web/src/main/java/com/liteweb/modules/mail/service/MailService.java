package com.liteweb.modules.mail.service;

import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.mail.Vo.MailVo;
import com.liteweb.modules.mail.exception.MailException;

import javax.mail.MessagingException;

public interface MailService {

    ResultResponse<Boolean> sendSimpleMail(MailVo mailVo);

    ResultResponse<Boolean> sendHtmlMail(MailVo mailVo) throws MessagingException;

    ResultResponse<Boolean> sendAuthMail(String to) throws MessagingException, MailException;
}
