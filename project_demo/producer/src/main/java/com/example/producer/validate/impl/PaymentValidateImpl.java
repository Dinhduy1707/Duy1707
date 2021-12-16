package com.example.producer.validate.impl;

import com.example.producer.config.redis.RedisConfig;
import com.example.producer.dto.PaymentDTO;
import com.example.producer.exception.*;
import com.example.producer.utils.DateUtil;
import com.example.producer.validate.PaymentValidate;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
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
            log.info("Validate apiID blank", paymentDTO.getApiID());
            throw new ApiBlankException();
        }
        if (!DateUtil.checkFormat(paymentDTO.getPayDate(), "yyyyMMddHHmmss")) {
            log.info("Validate payDate fail format", paymentDTO.getPayDate());
            throw new PaymentDateException();
        }
        if (StringUtils.isBlank(paymentDTO.getOrderCode())) {
            log.info("Validate orderCode blank", paymentDTO.getOrderCode());
            throw new OrderCodeException();
        }
        Double differenceAmount = Double.parseDouble(paymentDTO.getRealAmount()) - paymentDTO.getDebitAmount();
        if (differenceAmount > 0) {
            log.info("Validate realAmount misTake", paymentDTO.getRealAmount(), paymentDTO.getDebitAmount());
            throw new RealAmountException();
        }
        if (paymentDTO.getPromotionCode() == null && differenceAmount != 0) {
            log.info("Validate promotionCode null", paymentDTO.getPromotionCode());
            throw new PromotionCodeException();
        }
    }

    public void isTokenKeyExisted(String token) {

        log.info("Start check and save tokenKey day");
        try (Jedis jedis = redisConfiguration.getResource()) {
            Long checkTokenExists = jedis.setnx(token, token);
            if (checkTokenExists == TOKEN_EXISTS) {
                log.info("tokenKey exists");
                throw new TokenKeyException();
            } else {
              // DateUtil.calculateTheRemainingTimeOfTheDay();
            }
        }
    }
}


