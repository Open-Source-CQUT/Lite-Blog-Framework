package com.lite.common.exception.handler;

import com.lite.common.dto.ResultResponse;
import com.lite.common.exception.BaseException;
import com.lite.common.utils.ResultResponseUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    HttpServletResponse response;

    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ResultResponse<String> BaseExceptionProcessor(Exception e) {
        e.printStackTrace();

        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            response.setStatus(baseException.getStatus());
            return ResultResponseUtils.error(baseException.getStatus(), baseException.getMessage());
        }

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResultResponseUtils.error(StringUtils.isBlank(e.getMessage()) ? e.toString() : e.getMessage());
    }
}
