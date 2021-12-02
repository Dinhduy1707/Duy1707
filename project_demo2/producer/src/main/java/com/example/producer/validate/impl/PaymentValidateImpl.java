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



    private final RedisConfig redisConfiguration;

    public PaymentValidateImpl(RedisConfig redisConfiguration) {
        this.redisConfiguration = redisConfiguration;
    }

    @Override
    public void validate(PaymentDTO paymentDTO) {

        log.info("Start validate input payDTO : {}", paymentDTO);
        if (StringUtils.isBlank(paymentDTO.getApiID())) {
            log.debug("Validate apiID blank, apiID: {}", paymentDTO.getApiID());
            throw new ApiBlankException();
        }
        if (!DateUtil.checkFormat(paymentDTO.getPayDate(), "yyyyMMddHHmmss")) {
            log.debug("Validate payDate  format fail: {}", paymentDTO.getPayDate());
        }
        if (StringUtils.isBlank(paymentDTO.getOrderCode())) {
            log.debug("Validate orderCode blank, orderCode: {}", paymentDTO.getOrderCode());
            throw new OrderCodeException();
        }
        Double differenceAmount = Double.parseDouble(paymentDTO.getRealAmount()) - paymentDTO.getDebitAmount();
        if (differenceAmount > 0) {
            log.debug("Validate realAmount misTake, realAmount mistake: {}", paymentDTO.getRealAmount());
            throw new RealAmountException();
        }
        if (paymentDTO.getPromotionCode() == null && differenceAmount != 0) {
            log.debug("Validate promotionCode null, promotionCode: {}", paymentDTO.getPromotionCode());
            throw new PromotionCodeException();
        }

        if (isTokenKeyExisted(paymentDTO.getTokenKey())) {
            log.debug("Token key duplicated, Token: {}", paymentDTO.getTokenKey());
            throw new TokenKeyException();
        }
    }

    public boolean isTokenKeyExisted(String token) {
        Jedis jedis = null;
        try {
            jedis = redisConfiguration.getResource();
            return jedis.exists(token);
        } catch (Exception e) {
            log.error("Save payment to redis failed. Exception: " + e);
            return false;
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
