package com.xiaotu.rediscache.config;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    //自定义缓存key生成策略
    @Bean
    public KeyGenerator keyGenerator(){
        return new KeyGenerator() {
            @Override
            public Object generate(Object target, Method method, Object... params) {
                StringBuffer sb = new StringBuffer();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params){
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }


    //缓存管理器
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory  connectionFactory) {
        RedisSerializationContext.SerializationPair serializationPair =
                RedisSerializationContext.SerializationPair.fromSerializer(getRedisSerializer());

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(30))
                .serializeValuesWith(serializationPair);
        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(redisCacheConfiguration).build();
    }

    private RedisSerializer<Object> getRedisSerializer(){
        return new GenericJackson2JsonRedisSerializer();
    }
//
//    @Bean
//    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//        //设置不同cacheName的过期时间
//        Map<String, RedisCacheConfiguration> configurations = new HashMap<>(16);
//        // 序列化方式
//        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = getJsonRedisSerializer();
//        RedisSerializationContext.SerializationPair<Object> serializationPair =
//                RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer);
//        // 默认的缓存时间
//        Duration defaultTtl = Duration.ofSeconds(20L);
//        // 用户模块的缓存时间
//        Duration userTtl = Duration.ofSeconds(50L);
//        // 默认的缓存配置
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
//                //.entryTtl(defaultTtl)
//                .serializeValuesWith(serializationPair);
//        // 自定义用户模块的缓存配置 自定义的配置可以覆盖默认配置（当前的模块）
////        configurations.put(CacheConstant.USER_CACHE_NAME, RedisCacheConfiguration.defaultCacheConfig()
////                //.entryTtl(userTtl)
////                .serializeValuesWith(serializationPair)
////        );
//
//        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
//                .cacheDefaults(redisCacheConfiguration)
//                .withInitialCacheConfigurations(configurations)
//                // 事物支持
//                .transactionAware()
//                .build();
//    }

//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {
//        StringRedisTemplate template = new StringRedisTemplate(factory);
//        setSerializer(template);// 设置序列化工具
//        template.afterPropertiesSet();
//        return template;
//    }
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
//        RedisTemplate<Object, Object> template = new RedisTemplate<>();
//        template.setConnectionFactory(factory);
//        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = getJsonRedisSerializer();
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        // key采用String的序列化方式
//        template.setKeySerializer(stringRedisSerializer);
//        // hash的key也采用String的序列化方式
//        template.setHashKeySerializer(stringRedisSerializer);
//        // value序列化方式采用jackson
//        template.setValueSerializer(jsonRedisSerializer);
//        // hash的value序列化方式采用jackson
//        template.setHashValueSerializer(jsonRedisSerializer);
//        // 支持事物
//        //template.setEnableTransactionSupport(true);
//        template.afterPropertiesSet();
//        return template;
//    }

    /**
     * 设置jackson的序列化方式
     */
    private Jackson2JsonRedisSerializer<Object> getJsonRedisSerializer() {
        Jackson2JsonRedisSerializer<Object> redisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        redisSerializer.setObjectMapper(om);
        return redisSerializer;
    }
}