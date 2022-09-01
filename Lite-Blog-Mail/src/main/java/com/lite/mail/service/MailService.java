package com.lite.mail.service;

import com.lite.mail.Vo.MailVo;
import com.lite.mail.exception.MailException;

import javax.mail.MessagingException;

public interface MailService {

    Boolean sendSimpleMail(MailVo mailVo);

    Boolean sendHtmlMail(MailVo mailVo) throws MessagingException;

    Boolean sendAuthMail(String to) throws MessagingException, MailException;
}
