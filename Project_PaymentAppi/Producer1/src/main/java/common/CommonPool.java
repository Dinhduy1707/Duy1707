package common;

import com.zaxxer.hikari.HikariDataSource;
import redis.clients.jedis.JedisSentinelPool;
import utils.BasicChannelPool;



public class CommonPool {
    public static HikariDataSource hikariDataSource;
    //public static JedisSentinelPool sentinelPool;
    public static BasicChannelPool basicChannelPool;

}
