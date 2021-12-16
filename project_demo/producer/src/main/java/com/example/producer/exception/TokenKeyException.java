package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class TokenKeyException  extends RuntimeException {

    public TokenKeyException() {
        super(ConstantPayment.TOKEN_KEY_EXISTS.getMessage());
    }
}
