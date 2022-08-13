package com.liteweb.convert.auth.rule;

import org.springframework.stereotype.Component;

@Component
public class GenderRule {

    public String toStr(Integer code){
        return code == 0 ? "女" : "男";
    }

    public Integer toCode(String gender){
        return "女".equals(gender) ? 0 : 1;
    }
}
