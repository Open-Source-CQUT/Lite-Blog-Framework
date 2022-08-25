package com.lite.cos.exception;

import com.lite.common.exception.BaseException;

public class CosFileException extends BaseException {

    public CosFileException(Integer status, String message) {
        super(status, message);
    }

}
