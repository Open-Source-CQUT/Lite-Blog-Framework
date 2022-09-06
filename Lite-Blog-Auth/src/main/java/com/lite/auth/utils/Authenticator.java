package com.lite.auth.utils;

import com.alibaba.fastjson2.JSON;
import com.lite.common.dto.token.JwtToken;
import com.lite.common.utils.DateUtils;
import com.lite.common.utils.JwtUtil;
import com.lite.auth.exception.AuthException;
import com.lite.common.serializer.RedisCache;
import com.lite.auth.vo.UserTokenVo;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class Authenticator {


    @Autowired
    RedisCache redisCache;

    @Autowired
    LiteBlogContextUtils liteBlogContextUtils;

    /**
     * 校验access-token
     *
     * @param token access-token
     * @return 是否成功
     */
    public Boolean authenticateAccessToken(String token) {

        //读取payload
        String payload = JwtUtil.parseAccessJwt(token).getSubject();

        //读取载荷
        UserTokenVo userVo = JSON.parseObject(payload, UserTokenVo.class);

        //初始化LocalUserInfo
        liteBlogContextUtils.initLocalUserInfo(userVo);

        //redis中获取不到则过期
        if (Objects.isNull(
                redisCache.getCacheObject(
                        JwtUtil.getRedisAccessKey(userVo.getMail(), userVo.getUuid())))) {
            throw new ExpiredJwtException(null, null, null);
        }

        return true;
    }

    public Boolean authenticateRefreshToken(String refreshToken, String accessToken) throws AuthException {

        Date absolutelyExpireTime = null;

        UserTokenVo accessPayload = null;

        try {
            JwtUtil.parseAccessJwt(accessToken);
        } catch (ExpiredJwtException e) {

            //获取预设的过期时间
            Long expireTime = e.getClaims().getExpiration().getTime();

            //计算出绝对过期时间
            absolutelyExpireTime = new Date(JwtUtil.JWT_TRANSITION_TTL + expireTime);

            accessPayload = JSON.parseObject(e.getClaims().getSubject(), UserTokenVo.class);

            //这个时候如果还有什么其他的异常直接返回false
        } catch (Exception e) {
            return false;
        }

        //允许过期时间已过
        if (Objects.isNull(absolutelyExpireTime) || absolutelyExpireTime.getTime() < System.currentTimeMillis()) {
            return false;
        }


        //读取payload
        String payload = JwtUtil.parseAccessJwt(refreshToken).getSubject();

        //读取载荷
        UserTokenVo refreshPayload = JSON.parseObject(payload, UserTokenVo.class);

        //uuid必须相同
        if (Objects.isNull(accessPayload) || !accessPayload.getUuid().equals(refreshPayload.getUuid())) {
            return false;
        }

        //access登陆时间只能大于等于refresh,绝对不可能小于refresh
        if (DateUtils.isBefore(accessPayload.getLoginTime(), refreshPayload.getLoginTime())) {
            return false;
        }

        //redis中获取不到则过期,refresh过期用户必须重新登陆
        return !Objects.isNull(
                redisCache.getCacheObject(
                        JwtUtil.getRedisRefreshKey(refreshPayload.getMail(), refreshPayload.getUuid())));
    }

    public JwtToken processAndGetAccessToken(UserTokenVo userVo) {

        //json转换
        String userVoJsonStr = JSON.toJSONString(userVo);

        //生成access-token
        JwtToken accessToken = JwtUtil.createAccessJWT(userVoJsonStr);

        //获取redisKey
        String accessKey = JwtUtil.getRedisAccessKey(userVo.getMail(), userVo.getUuid());

        //redis处理
        redisCacheToken(accessKey, accessToken);

        return accessToken;
    }

    public JwtToken processAndGetRefreshToken(UserTokenVo userVo) {

        //json转换
        String userVoJsonStr = JSON.toJSONString(userVo);

        //生成access-token
        JwtToken accessToken = JwtUtil.createRefreshJWT(userVoJsonStr);

        //获取redisKey
        String accessKey = JwtUtil.getRedisRefreshKey(userVo.getMail(), userVo.getUuid());

        //redis处理
        redisCacheToken(accessKey, accessToken);

        return accessToken;
    }

    private void redisCacheToken(String key, JwtToken token) {

        //存入redis
        redisCache.setCacheObject(key, token);

        //设置过期时间
        redisCache.expire(key, JwtUtil.getTTL(token));
    }

}
