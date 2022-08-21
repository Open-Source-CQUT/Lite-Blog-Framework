package com.liteweb.modules.common.exception.handler;

import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.common.exception.BaseException;
import com.liteweb.modules.common.utils.LiteBlogContextUtils;
import com.liteweb.modules.common.utils.ResultResponseUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    LiteBlogContextUtils contextUtils;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultResponse<String> BaseExceptionProcessor(Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            contextUtils.setResponseStatus(baseException.getStatus());
            return ResultResponseUtils.error(baseException.getStatus(), baseException.getMessage());
        }

        contextUtils.setResponseStatus(HttpStatus.BAD_REQUEST.value());
        return ResultResponseUtils.error(Strings.isBlank(e.getMessage()) ? e.toString() : e.getMessage());
    }
}
