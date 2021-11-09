package com.example.syncer;

import com.example.common.CommonPool;
import com.example.common.JsonCustom;
import com.example.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisSyncer {
    private static final Logger LOG = LogManager.getLogger(RedisSyncer.class);

    public static void savaData(String key, List<User> listUser) {
        if (listUser == null) return;
        try (Jedis redis = CommonPool.sentinelPool.getResource()) {
            redis.del(key);
            LOG.info("Begin write data into redis ");
            for (User user : listUser) {
                LOG.info("Data with key: " + key + "value " + JsonCustom.convertObjectToJson(user));
                redis.lpush(key, JsonCustom.convertObjectToJson(user));
                LOG.info("Insert success");
            }
            LOG.info("End write data into redis");
        } catch (Exception e) {
            LOG.error("Error insert data to redis: ", e);
        }
    }
}
