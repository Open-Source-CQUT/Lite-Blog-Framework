package com.lite.common.config.redis;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.support.spring.data.redis.GenericFastJsonRedisSerializer;
import com.lite.common.entity.RedisEvalRes;
import com.lite.common.serializer.RedisJsonScript;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.cache.interceptor.SimpleKeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.Collections;


@Configuration
public class RedisCacheConfig {

    /**
     * 修改RedisTemplate的序列化方式
     *
     * @param connectionFactory RedisConnectionFactory
     * @return RedisTemplate<Object, Object>
     */
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        GenericFastJsonRedisSerializer serializer = genericFastJsonRedisSerializer();

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }

    /**
     * 自定义缓存时生成key值的策略
     *
     * @return 生成的key
     */
    @Bean("BusinessKeyGenerator")
    public KeyGenerator keyGenerator() {
        return ((target, method, params) -> {
            //拿到被代理的对象
            Class<?> targetClass = AopProxyUtils.ultimateTargetClass(target);

            return new StringBuilder()
                    .append(targetClass.getSimpleName())
                    .append(".params(")
                    .append(SimpleKeyGenerator.generateKey(params))
                    .append(")");
        });
    }


    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        return RedisCacheManager
                .builder(RedisCacheWriter
                        .nonLockingRedisCacheWriter(connectionFactory))
                //开启事务
                .transactionAware()
                //开启缓存收集
                .enableStatistics()
                //修改默认设置
                .cacheDefaults(RedisCacheConfiguration
                        .defaultCacheConfig()
                        //将默认的value序列化器从jdk序列化改为json序列化
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(genericFastJsonRedisSerializer()))
                        //缓存过期时间
                        .entryTtl(Duration.ofHours(1)))
                .build();
    }


    /**
     * 通用Redis序列化器
     * @return GenericFastJsonRedisSerializer
     */
    public GenericFastJsonRedisSerializer genericFastJsonRedisSerializer(){
        return new GenericFastJsonRedisSerializer(new String[]{});
    }

}
