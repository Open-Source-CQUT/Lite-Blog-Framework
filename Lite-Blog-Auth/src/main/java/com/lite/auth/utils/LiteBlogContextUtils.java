package com.lite.auth.utils;

import com.lite.common.serializer.RedisCache;
import com.lite.common.utils.JwtUtil;
import com.lite.auth.vo.UserTokenVo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Getter
@Component
public class LiteBlogContextUtils {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * 每一个线程中独有的用户信息对象,在拦截器完成token校验后将自动注入此对象
     */
    private final ThreadLocal<UserTokenVo> localUserInfo = new ThreadLocal<>();

    //从header中获取payload
    public UserTokenVo getUserContextInfo() {
        return JwtUtil.parseObject(request.getHeader(JwtUtil.JWT_ACCESS_KEY), UserTokenVo.class, JwtUtil.JWT_ACCESS_KEY);
    }

    public void initLocalUserInfo(UserTokenVo userTokenVo){
        localUserInfo.set(userTokenVo);
    }

    public UserTokenVo getLocalUserInfo() {
        return localUserInfo.get();
    }

    public void clearLocalUserInfo() {
        localUserInfo.remove();
    }


    public void setResponseStatus(Integer status) {
        response.setStatus(status);
    }
}
