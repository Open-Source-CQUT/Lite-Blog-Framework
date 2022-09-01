package com.lite.business.exceptioin;

import com.lite.common.exception.BaseException;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/1 19:15
 */
public class ArticleException extends BaseException {

    public ArticleException(Integer httpStatus, String message) {
        super(httpStatus, message);
    }
}
