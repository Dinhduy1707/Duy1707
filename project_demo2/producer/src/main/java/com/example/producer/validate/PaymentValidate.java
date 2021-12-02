package com.example.producer.validate;

import com.example.producer.dto.PaymentDTO;

public interface PaymentValidate {
    void validate(PaymentDTO paymentDTO);
    boolean isTokenKeyExisted(String token);
}
