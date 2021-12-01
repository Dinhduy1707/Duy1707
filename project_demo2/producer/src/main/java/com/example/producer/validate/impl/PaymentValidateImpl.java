package com.example.producer.validate.impl;

import com.example.producer.config.redis.RedisConfig;
import com.example.producer.dto.PaymentDTO;
import com.example.producer.exception.*;
import com.example.producer.utils.DateUtil;
import com.example.producer.validate.PaymentValidate;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
@Log4j2
public class PaymentValidateImpl implements PaymentValidate {


    private static final Long TOKEN_EXISTS = 0L;

    private final RedisConfig redisConfiguration;

    public PaymentValidateImpl(RedisConfig redisConfiguration) {
        this.redisConfiguration = redisConfiguration;
    }

    @Override
    public void validate(PaymentDTO paymentDTO) {

        log.info("Start validate input payDTO");
        if (StringUtils.isBlank(paymentDTO.getApiID())) {
            log.info("Validate apiID blank");
            throw new ApiBlankException();
        }
        if (!DateUtil.checkFormat(paymentDTO.getPayDate(), "yyyyMMddHHmmss")) {
            log.info("Validate paydate fail format");
        }
        if (StringUtils.isBlank(paymentDTO.getOrderCode())) {
            log.info("Validate orderCode blank");
            throw new OrderCodeException();
        }
        Double differenceAmount = Double.parseDouble(paymentDTO.getRealAmount()) - paymentDTO.getDebitAmount();
        if (differenceAmount > 0) {
            log.info("Validate realAmount misTake");
            throw new RealAmountException();
        }
        if (paymentDTO.getPromotionCode() == null && differenceAmount != 0) {
            log.info("Validate promotionCode null");
            throw new PromotionCodeException();
        }
    }

    public void isTokenKeyExisted(String token) {
        Jedis jedis = null;
        try {
            jedis = redisConfiguration.getResource();
            Long result = jedis.setnx(token, token);
            if (TOKEN_EXISTS.equals(result)) {
                throw new TokenKeyException();
            }
        } catch (Exception e) {
            log.error("Save payment to redis failed. Exception: " + e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
