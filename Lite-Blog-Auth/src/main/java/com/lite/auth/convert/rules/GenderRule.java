package com.lite.auth.convert.rules;

import com.lite.common.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class GenderRule {

    public String toStr(Integer code) {
        return code == 0 ? "女" : "男";
    }

    public Integer toCode(String gender) {
        return "女".equals(gender) ? 0 : 1;
    }

    public String dateToStr(LocalDateTime dateTime){
        return DateUtils.formatDefault(dateTime);
    }
}
