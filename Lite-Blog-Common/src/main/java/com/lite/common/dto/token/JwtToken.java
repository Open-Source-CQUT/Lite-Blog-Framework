package com.lite.common.dto.token;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    //后端从header中获取的key
    private String jwtKey;

    //token字符串
    private String token;

}
