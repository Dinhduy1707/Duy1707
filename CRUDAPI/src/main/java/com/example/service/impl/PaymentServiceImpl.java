package com.example.service.impl;

import com.example.common.GsonCustom;
import com.example.constans.ResponseCode;
import com.example.dto.Response;
import com.example.entity.Payment;
import com.example.service.PaymentService;
import com.example.service.RedisService;
import com.example.validate.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final RedisService redisService;

    public PaymentServiceImpl(RedisService redisService) {
        this.redisService = redisService;
    }


    @Override
    public Response savePayment(Payment payment) {
        log.info("Begin to save payment: {}", GsonCustom.getInstance().toJson(payment));
        //check null
        if (payment.isNull()) {
            log.info("Payment {} :  Not null ");
            return Response.to(ResponseCode.NOT_NULL);
        }
        //checkSum
        if (!Validator.checkSum(payment)) {
            return Response.to(ResponseCode.BANK_CODE_NOT_NULL);
        }

        //checkRedis
        if (saveRedis(payment)) {
            log.info("Insert into data success: {}", GsonCustom.getInstance().toJson(payment));
            return Response.to(ResponseCode.SUCCESS);

        }

        log.error("Insert data fail: {}", GsonCustom.getInstance().toJson(payment));
        return Response.to(ResponseCode.INSERT_DATA_FAIL);

    }

    private boolean saveRedis(Payment payment) {
        return redisService.cacheHashset(payment.getBankCode(), payment.getTokenKey(), payment.getJsonData());
    }
}
