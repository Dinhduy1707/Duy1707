package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    @Value("0.0.0.0")
    private String redisHost;
    @Value("${spring.redis.port}")
    private int redisPort;
    @Value(("30"))
    private int timeout;
    @Value("10")
    private int maxIdle;

    private static final JedisPool jedisPool = new JedisPool();

    @Bean
    public JedisPool getJedisPoolFactory() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        JedisPool jedisPool = new JedisPool(poolConfig, redisHost, redisPort, timeout);
        return jedisPool;
    }

    private Jedis getResource() {
        return jedisPool.getResource();
    }


}
