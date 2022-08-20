package com.liteweb.modules.common.exception.handler;

import com.liteweb.modules.common.dto.ResultResponse;
import com.liteweb.modules.common.exception.BaseException;
import com.liteweb.modules.common.utils.ResultResponseUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResultResponse<String> BaseExceptionProcessor(Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return ResultResponseUtils.error(baseException.getStatus(), baseException.getMessage());
        }

        return ResultResponseUtils.error(Strings.isBlank(e.getMessage()) ? e.toString() : e.getMessage());
    }
}
