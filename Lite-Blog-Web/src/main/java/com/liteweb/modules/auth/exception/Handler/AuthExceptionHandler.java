package com.liteweb.modules.auth.exception.Handler;

import com.liteweb.modules.auth.exception.AuthException;
import com.liteweb.modules.auth.exception.PasswordErrorException;
import com.liteweb.modules.auth.exception.UserDuplicateException;
import com.liteweb.modules.auth.exception.UserNotFoundException;
import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.common.utils.ResultResponseUtils;
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
    public ResultResponse<String> AuthExceptionProcessor(Exception e) {
        return ResultResponseUtils.error(e.getMessage());
    }

}
