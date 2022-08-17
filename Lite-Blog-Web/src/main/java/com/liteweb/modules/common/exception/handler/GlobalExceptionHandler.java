package com.liteweb.modules.common.exception.handler;

import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.utils.tool.ResultResponseUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultResponse<String> AuthExceptionProcessor(Exception e) {
        return ResultResponseUtils.error(e.getMessage());
    }
}
