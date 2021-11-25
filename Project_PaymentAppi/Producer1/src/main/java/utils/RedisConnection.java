package utils;


import lombok.extern.log4j.Log4j2;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

@Log4j2
public class RedisConnection {
    private static JedisPool jedisPool = null;
    private static JedisPoolConfig poolConfig = new JedisPoolConfig();

    static {
        log.info("Begin connection reids ");
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());

        jedisPool = new JedisPool(poolConfig, "localhost");
        System.out.println("Thành công ");
        log.info("Begin successes ");

    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    private RedisConnection() {

    }
}
