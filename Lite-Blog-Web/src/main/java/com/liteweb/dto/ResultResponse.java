package com.liteweb.dto;

import com.liteweb.utils.StringUtils;
import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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
        this(data, code, StringUtils.BLANK);
    }

    public ResultResponse(Integer code, String msg) {
        this(null,code,msg);
    }
}
