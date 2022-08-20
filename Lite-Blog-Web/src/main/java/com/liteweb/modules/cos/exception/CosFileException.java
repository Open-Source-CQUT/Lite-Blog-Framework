package com.liteweb.modules.cos.exception;

import com.liteweb.modules.common.exception.BaseException;

public class CosFileException extends BaseException {

    public CosFileException(Integer status, String message) {
        super(status, message);
    }

}
