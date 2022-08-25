package com.lite.mail.service;

import com.lite.common.dto.ResultResponse;
import com.lite.mail.Vo.MailVo;
import com.lite.mail.exception.MailException;

import javax.mail.MessagingException;

public interface MailService {

    ResultResponse<Boolean> sendSimpleMail(MailVo mailVo);

    ResultResponse<Boolean> sendHtmlMail(MailVo mailVo) throws MessagingException;

    ResultResponse<Boolean> sendAuthMail(String to) throws MessagingException, MailException;
}
