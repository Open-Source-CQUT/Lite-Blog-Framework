package com.lite.mail.Vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthMailVo {

    private String authCode;

    private String to;

    private String date;

}
