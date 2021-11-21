package com.example.repository;

import com.example.entity.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentRepository {
    public static final String key = "payment";
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate template;

    public Payment saveRedis(Payment payment) {
        template.opsForValue().set(payment, key, payment.getId());

        return payment;
    }


}
