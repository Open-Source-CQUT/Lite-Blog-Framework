package com.lite.mail.service.impl;

import com.lite.common.i18n.SystemMessages;
import com.lite.common.serializer.RedisCache;
import com.lite.common.utils.DateUtils;
import com.lite.mail.Vo.AuthMailVo;
import com.lite.mail.exception.MailException;
import com.lite.mail.service.MailService;
import com.lite.mail.utils.MailUtils;
import com.lite.mail.Vo.MailVo;
import com.lite.mail.config.MailConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Objects;

@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    MailConfig mailConfig;

    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    RedisCache redisCache;

    @Autowired
    TemplateEngine templateEngine;

    @Override
    public Boolean sendSimpleMail(MailVo mailVo) {
        log.info(String.format("正在发送 TEXT 邮件 | to:%s | subject:%s", mailVo.getTarget(), mailVo.getSubject()));

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(mailConfig.getUsername());
        simpleMailMessage.setTo(mailVo.getTarget());
        simpleMailMessage.setSubject(mailVo.getSubject());
        simpleMailMessage.setText(mailVo.getContent());

        javaMailSender.send(simpleMailMessage);

        log.info("邮件发送成功");
        return true;
    }

    @Override
    public Boolean sendHtmlMail(MailVo mailVo) throws MessagingException {
        log.info(String.format("正在发送 HTML 邮件 | to:%s | subject:%s", mailVo.getTarget(), mailVo.getSubject()));

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        //配置邮件
        helper.setTo(mailVo.getTarget());
        helper.setFrom(mailConfig.getUsername());
        helper.setSubject(mailVo.getSubject());
        helper.setText(mailVo.getContent(), true);

        javaMailSender.send(message);

        log.info("邮件发送成功");
        return true;
    }

    @Override
    public Boolean sendAuthMail(String to) throws MessagingException, MailException {

        //生成验证码
        String authCode = MailUtils.generateAuthCode();

        //包装实体类
        AuthMailVo authMailVo = new AuthMailVo(authCode, to, DateUtils.formatNow());

        //创建参数上下文
        Context context = new Context();
        context.setVariable(MailUtils.CODE_VARIABLE, authCode);

        //解析html
        String content = templateEngine.process(MailUtils.TEMPLATE_PATH, context);

        //验证存入redis
        String key = MailUtils.getMailRedisKey(to);

        //如果redis中已经存在
        if (!Objects.isNull(redisCache.getCacheObject(key))) {
            throw new MailException(HttpStatus.BAD_REQUEST.value(), SystemMessages.get("info.mail.auth.repeat"));
        }

        redisCache.setCacheObject(key, authMailVo);

        //5分钟后过期
        redisCache.expire(key, DateUtils.MINUTES * 5);

        Boolean resultResponse;

        //发送邮件
        try {
            resultResponse = sendHtmlMail(new MailVo(to, SystemMessages.get("info.mail.auth.subject"), content));
        } catch (Exception e) {
            //如果过程中发送什么异常直接抛出，并将redis中的数据删掉
            redisCache.deleteObject(key);
            throw e;
        }

        return resultResponse;
    }
}
