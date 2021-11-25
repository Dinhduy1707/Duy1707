package syncer;

import common.CommonPool;
import common.JsonCustom;
import lombok.extern.log4j.Log4j2;
import model.Payment;
import redis.clients.jedis.Jedis;
import validate.impl.ValidateUserImpl;

@Log4j2
public class RedisSyncer {
    static ValidateUserImpl validateUser = new ValidateUserImpl();


    public static Payment saveData(String key, Payment user) throws Exception {

        validateUser.checkTokenExitAndSaveRedis(user.getTokenKey());

        validateUser.validate(user);


        try (Jedis redis = CommonPool.jedisPool.getResource()) {
            log.info("Start write data into redis");
            redis.lpush(key, JsonCustom.convertObjectToJson(user));
            log.info("Insert success");

            log.info("End write data into redis");
        } catch (Exception e) {
            log.error("Error insert data to redis: ", e);
        }
        return user;
    }

}
