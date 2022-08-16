package com.liteweb.exception.Handler;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.exception.auth.AuthException;
import com.liteweb.exception.auth.PasswordErrorException;
import com.liteweb.exception.auth.UserDuplicateException;
import com.liteweb.exception.auth.UserNotFoundException;
import com.liteweb.utils.tool.ResultResponseUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 主要用来处理一些表单校验异常
 */
@RestControllerAdvice
public class AuthExceptionHandler {

    @ResponseBody
    @ExceptionHandler({AuthException.class, UserDuplicateException.class, UserNotFoundException.class, PasswordErrorException.class})
    public ResultResponse<String> AuthExceptionProcessor(Exception e){
        return ResultResponseUtils.error(e.getMessage());
    }

}
