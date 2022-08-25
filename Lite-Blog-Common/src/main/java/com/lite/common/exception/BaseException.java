package com.lite.common.exception;

import lombok.Data;

@Data
public class BaseException extends Exception {

    private Integer status;

    public BaseException(Integer httpStatus, String message) {
        super(message);
        this.status = httpStatus;
    }
}
