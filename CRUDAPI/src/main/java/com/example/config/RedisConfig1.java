package com.example.config;

import com.example.common.GsonCustom;
import com.example.common.Property;
import com.example.model.User;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Protocol;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class RedisConfig1 {
    private static final Logger LOG = LogManager.getLogger(RedisConfig1.class);
    private static final String REDIS_MASTER_NAME = "redis_master_name";
    private static final String REDIS_PASSWORD = "redis_password";
    private static final String REDIS_HOST_MASTER = "redis_host_master";
    private static final String REDIS_PORT_MASTER = "redis_port_master";
    private static final String MAX_DATABASE = "max_database";
    private static Properties properties = Property.getInstance();

    private static GenericObjectPoolConfig config = new GenericObjectPoolConfig();
    private static JedisSentinelPool sentinelPool = null;
    User user = new User();

    static {
        try {
            LOG.info("Begin create ");
            LOG.info("Begin create sentinelPool");
            Set<String> sentinels = new HashSet<>();
            sentinels.add(new HostAndPort(properties.getProperty(REDIS_HOST_MASTER), Integer.parseInt(properties.getProperty(REDIS_PORT_MASTER))).toString());
            sentinelPool = new JedisSentinelPool(properties.getProperty(REDIS_MASTER_NAME), sentinels, config, Protocol.DEFAULT_TIMEOUT, properties.getProperty(REDIS_PASSWORD), Integer.parseInt(properties.getProperty(MAX_DATABASE)));
            LOG.info("Host and Port: " + sentinelPool.getCurrentHostMaster().toString());
            LOG.info("End create sentinelPool");
        } catch (Exception e) {
            LOG.error("Error create sentinelPool: ", e);
        }



    }



    public static JedisSentinelPool getJedisSentinelPool() {
        return sentinelPool;
    }

    private RedisConfig1() {
    }

}
