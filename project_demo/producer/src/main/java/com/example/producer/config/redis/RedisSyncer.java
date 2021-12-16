package com.example.producer.config.redis;

import com.example.producer.dto.PaymentDTO;

public interface RedisSyncer {
    public void savaDataRedis(String key , PaymentDTO paymentDTO) ;

}
