package com.lite.auth.utils;

import com.lite.auth.vo.UserTokenVo;
import com.lite.common.serializer.RedisCache;
import com.lite.common.utils.JwtUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;

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

    public void initLocalUserInfo(UserTokenVo userTokenVo) {
        localUserInfo.set(userTokenVo);
    }

    public String getClientIpAddress() {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (ip.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                assert inet != null;
                ip = inet.getHostAddress();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15) {
            if (ip.indexOf(",") > 0) {
                ip = ip.substring(0, ip.indexOf(","));
            }
        }
        return ip;
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
