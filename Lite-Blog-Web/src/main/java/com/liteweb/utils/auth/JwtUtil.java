package com.liteweb.utils.auth;

import com.liteweb.utils.serializer.PasswordEncoder;
import com.liteweb.utils.tool.DateUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
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
    private static final Long JWT_ACCESS_TTL = DateUtils.HOURS * 2;// 2小时

    //长时效refresh token
    private static final Long JWT_REFRESH_TTL = DateUtils.DAY * 2;// 2天

    //设置秘钥明文
    public static final String JWT_ACCESS_KEY = PasswordEncoder.enCode("Lite-Blog-Access-Key");

    public static final String JWT_REFRESH_KEY = PasswordEncoder.enCode("Lite-Blog-Refresh-Key");

    //签发者
    public static final String ISSUER = PasswordEncoder.enCode("Lite-Blog");

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static String createJWT(String subject) {
        return createJWT(subject, null, JWT_ACCESS_KEY);
    }


    public static String createJWT(String subject, Long ttlMillis, String key) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID(), key);
        return builder.compact();
    }

    public static String createRefreshJWT(String subject) {
        return createJWT(subject, JWT_REFRESH_TTL, JWT_REFRESH_KEY);
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
    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid, String key) {

        //获取签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //设置私钥
        SecretKey secretKey = generalKey(key);

        //设置签发时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //设置过期时间
        long expMillis = nowMillis + (ttlMillis == null ? JwtUtil.JWT_ACCESS_TTL : ttlMillis);
        Date expDate = new Date(expMillis);

        //构建jwt
        return Jwts.builder()
                //唯一的ID
                .setId(uuid)
                // 主题  可以是JSON数据
                .setSubject(subject)
                // 签发者
                .setIssuer(ISSUER)
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                //设置过期时间
                .setExpiration(expDate);
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

    public static String parseAccessJwt(String jwt) {
        return parseJWT(jwt, JWT_ACCESS_KEY).getSubject();
    }

    public static String parseRefreshJwt(String jwt) {
        return parseJWT(jwt, JWT_REFRESH_KEY).getSubject();
    }
}
