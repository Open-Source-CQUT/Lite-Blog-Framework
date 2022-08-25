package com.lite.common.utils;

import com.lite.common.dto.ResultResponse;
import org.springframework.http.HttpStatus;

public class ResultResponseUtils {

    /**
     * @param data 要传输的数据
     * @param msg  信息
     * @return ResultResponse<T> 包装好的代表<成功>状态的响应类
     */
    public static <T> ResultResponse<T> success(T data, String msg) {
        return new ResultResponse<>(data, HttpStatus.OK.value(), msg);
    }

    public static <T> ResultResponse<T> success(String msg) {
        return new ResultResponse<>(HttpStatus.OK.value(), msg);
    }

    public static <T> ResultResponse<T> success(T data) {
        return new ResultResponse<>(data, HttpStatus.OK.value());
    }

    /**
     * @param data 要传输的数据
     * @param msg  信息
     * @return ResultResponse<T> 包装好的代表<失败>状态的响应类
     */
    public static <T> ResultResponse<T> error(T data, String msg) {
        return new ResultResponse<>(data, HttpStatus.BAD_REQUEST.value(), msg);
    }

    public static <T> ResultResponse<T> error(String msg) {
        return new ResultResponse<>(HttpStatus.BAD_REQUEST.value(), msg);
    }

    public static <T> ResultResponse<T> error(T data) {
        return new ResultResponse<>(data, HttpStatus.BAD_REQUEST.value());
    }

    public static <T> ResultResponse<T> error(Integer code, String msg) {
        return new ResultResponse<>(null, code, msg);
    }

}
