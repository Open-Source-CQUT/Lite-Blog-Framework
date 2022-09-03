package com.lite.business.entity.status;

/**
 * @author Stranger
 * @version 1.0
 * @description: TODO
 * @date 2022/9/2 20:14
 */
public enum Status {

    UN_PUBLISHED(1L),

    REVIEW(2L),

    FAILED(3L),

    PUBLISHED(4L);

    final Long val;

    Status(Long val) {
        this.val = val;
    }

    public Long val(){
        return val;
    }
}
