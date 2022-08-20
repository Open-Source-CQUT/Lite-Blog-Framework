package com.liteweb.modules.common.utils;

import com.liteweb.modules.auth.utils.JwtUtil;
import com.liteweb.modules.auth.vo.UserTokenVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
@Component
public class LiteBlogContextUtils {

    @Autowired
    HttpServletRequest request;

    @Autowired
    HttpServletResponse response;

    //从header中获取payload
    public UserTokenVo getUserContextInfo() {
        return JwtUtil.parseObject(request.getHeader(JwtUtil.JWT_ACCESS_KEY), UserTokenVo.class, JwtUtil.JWT_ACCESS_KEY);
    }
}
