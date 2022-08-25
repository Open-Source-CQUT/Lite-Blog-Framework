package com.lite.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> {
    /**
     * 数据
     */
    private T data;

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 信息
     */
    private String msg;

    public ResultResponse(T data, Integer code) {
        this(data, code, null);
    }

    public ResultResponse(Integer code, String msg) {
        this(null, code, msg);
    }
}
