package com.liteweb.exception.lang;

import org.springframework.http.HttpStatus;

public enum LiteBlogExceptionStatus {

    //200
    LOGIN_OK(HttpStatus.OK.value(), "用户登陆成功!",HttpStatus.Series.SUCCESSFUL),

    REGISTER_OK(HttpStatus.OK.value(), "用户注册成功!",HttpStatus.Series.SUCCESSFUL),

    ACCESS_REFRESH_OK(HttpStatus.OK.value(), "Access-Token 刷新成功!",HttpStatus.Series.SUCCESSFUL),

    //400
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "用户不存在!", HttpStatus.Series.CLIENT_ERROR),

    PASSWORD_ERROR(HttpStatus.BAD_REQUEST.value(), "用户密码错误!",HttpStatus.Series.CLIENT_ERROR),

    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), "用户已经存在!",HttpStatus.Series.CLIENT_ERROR),

    ACCESS_EXPIRED(HttpStatus.UNAUTHORIZED.value(), "Access-Token 已失效!",HttpStatus.Series.CLIENT_ERROR),

    ACCESS_NULL(HttpStatus.FORBIDDEN.value(), "Access-Token 不能为空!",HttpStatus.Series.CLIENT_ERROR),
    ACCESS_ILLEGAL(HttpStatus.FORBIDDEN.value(), "Access-Token 不合法!",HttpStatus.Series.CLIENT_ERROR),

    REFRESH_EXPIRED(HttpStatus.UNAUTHORIZED.value(), "Refresh-Token 已失效!",HttpStatus.Series.CLIENT_ERROR),

    REFRESH_NULL(HttpStatus.FORBIDDEN.value(), "Refresh-Token 不能为空!",HttpStatus.Series.CLIENT_ERROR),

    REFRESH_ILLEGAL(HttpStatus.FORBIDDEN.value(), "Refresh-Token 不合法!",HttpStatus.Series.CLIENT_ERROR),

    //500
    REGISTER_FAIL(HttpStatus.INTERNAL_SERVER_ERROR.value(), "注册失败!",HttpStatus.Series.SERVER_ERROR);


    private final Integer code;

    private final String value;

    private final HttpStatus.Series series;


    LiteBlogExceptionStatus(Integer code, String value, HttpStatus.Series series) {
        this.code = code;
        this.value = value;
        this.series = series;
    }

    public String value() {
        return value;
    }

    public Integer code() {
        return code;
    }

    public HttpStatus.Series series() {
        return series;
    }
}
