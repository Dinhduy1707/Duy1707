package com.example.service.impl;

import com.example.config.RedisConfig;
import com.example.service.RedisService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

@Service
@Log4j2
public class RedisServiceImpl implements RedisService {
    private final RedisConfig redisConfig;

    public RedisServiceImpl(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    @Override
    public boolean cacheHashset(String key, String field, String value) {

        Jedis jedis = null;
        try {
            jedis = redisConfig.getJedisPoolFactory().getResource();
            log.info("Begin insert data to redis");
            jedis.hset(key, field, value);
            return true;
        } catch (Exception e) {
            log.error("Error insert data to  redis");
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
