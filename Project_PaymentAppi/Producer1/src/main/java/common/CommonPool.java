package common;

import com.zaxxer.hikari.HikariDataSource;
import redis.clients.jedis.JedisPool;
import utils.BasicChannelPool;


import java.util.concurrent.ExecutorService;


public class CommonPool {
    public static HikariDataSource hikariDataSource;
    public static JedisPool jedisPool;
    public static BasicChannelPool basicChannelPool;
    public static ExecutorService threadPool;


}
