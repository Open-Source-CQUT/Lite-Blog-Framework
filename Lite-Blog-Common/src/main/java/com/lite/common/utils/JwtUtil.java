package com.lite.common.utils;

import com.alibaba.fastjson2.JSON;
import com.lite.common.dto.token.JwtToken;
import com.lite.common.serializer.PasswordEncoder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 */
public class JwtUtil {

    //短时效token
    public static final Long JWT_ACCESS_TTL = DateUtils.HOURS * 2;// 2小时
    //长时效refresh token
    public static final Long JWT_REFRESH_TTL = DateUtils.DAY * 7;// 7天

    //允许过渡时间
    public static final Long JWT_TRANSITION_TTL = DateUtils.MINUTES * 10;//10分钟

    //设置header的key
    public static final String JWT_ACCESS_KEY = PasswordEncoder.enCode("Lite-Blog-Access-Key");

    public static final String JWT_REFRESH_KEY = PasswordEncoder.enCode("Lite-Blog-Refresh-Key");

    //私钥
    private static final String JWT_SECRET_ACCESS_KEY = PasswordEncoder.enCode("VegetableProgrammer-Access-key");

    private static final String JWT_SECRET_REFRESH_KEY = PasswordEncoder.enCode("VegetableProgrammer-Refresh-key");

    //签发者
    public static final String ISSUER = PasswordEncoder.enCode("Lite-Blog");

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static JwtToken createAccessJWT(String subject) {
        return createJWT(subject, JWT_ACCESS_TTL, JWT_ACCESS_KEY, JWT_SECRET_ACCESS_KEY);
    }

    public static JwtToken createAccessJWT(String subject, Long ttl) {
        return createJWT(subject, ttl, JWT_ACCESS_KEY, JWT_SECRET_ACCESS_KEY);
    }

    public static JwtToken createRefreshJWT(String subject) {
        return createJWT(subject, JWT_REFRESH_TTL, JWT_REFRESH_KEY, JWT_SECRET_REFRESH_KEY);
    }

    public static JwtToken createRefreshJWT(String subject, Long ttl) {
        return createJWT(subject, ttl, JWT_REFRESH_KEY, JWT_SECRET_REFRESH_KEY);
    }

    public static JwtToken createJWT(String subject, Long ttlMillis, String key, String scKey) {
        return jwtTokenWrap(subject, ttlMillis, getUUID(), key, scKey);
    }


    /**
     * 构建jwt
     *
     * @param subject   存储的数据
     * @param ttlMillis 过期时间
     * @param uuid      uuid
     * @param key       私钥
     * @return jwtBuilder
     */
    private static JwtToken jwtTokenWrap(String subject, Long ttlMillis, String uuid, String key, String scKey) {

        //获取签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //设置私钥
        SecretKey secretKey = generalKey(scKey);

        //设置签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //设置过期时间
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成token字符串
        String token = Jwts.builder()
                //唯一的ID
                .setId(uuid)
                //主题  可以是JSON数据
                .setSubject(subject)
                //签发者
                .setIssuer(ISSUER)
                //签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                //设置过期时间
                .setExpiration(expDate)
                //打包生成token
                .compact();

        //包装成JwtToken类返回
        return new JwtToken(key, token);
    }

    /**
     * @return 生成加密后的秘钥 secretKey
     */
    public static SecretKey generalKey(String key) {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.JWT_ACCESS_KEY);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static Claims parseJWT(String jwt, String key) {
        SecretKey secretKey = generalKey(key);
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static Claims parseAccessJwt(String jwt) {
        return parseJWT(jwt, JWT_ACCESS_KEY);
    }

    public static Claims parseRefreshJwt(String jwt) {
        return parseJWT(jwt, JWT_REFRESH_KEY);
    }


    public static String getRedisAccessKey(String mail, String uuid) {
        return String.format("%s-%s-%s", uuid, JWT_ACCESS_KEY, mail);
    }

    public static String getRedisRefreshKey(String mail, String uuid) {
        return String.format("%s-%s-%s", uuid, JWT_REFRESH_KEY, mail);
    }

    public static <T> T parseObject(String jwt, Class<T> clazz, String key) {

        return JWT_ACCESS_KEY.equals(key) ?
                JSON.parseObject(parseAccessJwt(jwt).getSubject(), clazz) :
                JSON.parseObject(parseRefreshJwt(jwt).getSubject(), clazz);
    }

    public static Long getTTL(JwtToken token) {
        return JWT_ACCESS_KEY.equals(token.getJwtKey()) ? JWT_ACCESS_TTL : JWT_REFRESH_TTL;
    }
}
