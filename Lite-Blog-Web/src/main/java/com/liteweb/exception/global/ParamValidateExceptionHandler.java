package com.liteweb.exception.global;

import com.liteweb.dto.global.ResultResponse;
import com.liteweb.utils.tool.ResultResponseUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ParamValidateExceptionHandler {

    /**
     * 方法参数校验异常
     *
     * @param ex 抛出的异常
     * @return 处理后的异常信息
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse<String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        String resultMsg = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .reduce(Strings.EMPTY, (result, element) -> result + element + ";");


        return ResultResponseUtils.error(resultMsg);
    }

    /**
     * 约束校验异常
     *
     * @param ex 抛出的异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultResponse<String> ConstraintViolationExceptionHandler(ConstraintViolationException ex) {
        String errMsg = ex
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .reduce(Strings.EMPTY, (result, element) -> result + element + ";");

        return ResultResponseUtils.error(errMsg);
    }

    /**
     * 绑定异常
     *
     * @param ex 抛出的异常
     * @return 异常信息
     */
    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResultResponse<String> BindExceptionHandler(BindException ex) {
        String resultMsg = ex
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .reduce(Strings.EMPTY, (result, element) -> result + element + ";");

        return ResultResponseUtils.error(resultMsg);
    }
}
