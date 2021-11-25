package validate.impl;

import constants.StatusCode;
import constants.StatusMessage;
import exception.TokenExitException;
import exception.ValidateException;
import lombok.extern.log4j.Log4j2;
import model.Payment;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import validate.CheckDay;
import validate.DateValidatorUtils;


@Log4j2
public class ValidateUserImpl {

    private static final Long TOKEN_EXITS_IN_REDIS = 0L;
    JedisPool jedisPool = new JedisPool();
    CheckDay checkDay = new CheckDay();

    public boolean validate(Payment user) throws Exception {
        if (StringUtils.isBlank(user.getTokenKey())) {
            log.error("Validate tokenKey must not be blank");
            throw new ValidateException(StatusCode.INCORRECT_FORMAT, StatusMessage.TOKEN_KEY_FORMAT_INCORRECT);
        }
        if (StringUtils.isBlank(user.getApiID())) {
            log.error("Validate  apiID must not be blank");
            throw new ValidateException(StatusCode.INCORRECT_FORMAT, StatusMessage.API_ID_NOT_BLANK);
        }

        if (!DateValidatorUtils.checkFormat(user.getPayDate(), "yyyyMMddHHmmss")) {
            throw new ValidateException(StatusCode.INCORRECT_FORMAT, StatusMessage.PAYDATE_FORMAT_INCORRECT);
        }

        if (StringUtils.isBlank(user.getOderCode())) {
            log.error("Validate oderCode must not blank");
            throw new ValidateException(StatusCode.INCORRECT_FORMAT, StatusMessage.ORDER_CODE_NOT_BLANK);
        }

        long realAmount = Long.parseLong(user.getRealAmount());
        long debitAmount = Long.parseLong(user.getDebitAmount());

        if (realAmount > debitAmount) {
            log.error("Validate realAmount must not greater than debitAmount");
            throw new ValidateException(StatusCode.INCORRECT_FORMAT, StatusMessage.AMOUNT_MONEY_ERROR);
        }

        if (realAmount != debitAmount) {
            if (StringUtils.isBlank(user.getPromotionCode())) {
                log.error("Validate promotionCode must not blank");
                throw new ValidateException(StatusCode.INCORRECT_FORMAT, StatusMessage.PROMOTION_CODE_NOT_NULL);
            }
        }
        return true;
    }

    public void checkTokenExitAndSaveRedis(String token) {
        Jedis jedis = jedisPool.getResource();
        long result = jedis.setnx(token, token);
        if (TOKEN_EXITS_IN_REDIS.equals(result)) {
            throw new TokenExitException("token fail");
        }
        jedis.expire(token, checkDay.calculateTheRemainingTimeOfTheDay());

    }


}
