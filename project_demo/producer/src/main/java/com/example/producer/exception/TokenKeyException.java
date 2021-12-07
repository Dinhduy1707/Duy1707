package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class TokenKeyException  extends RuntimeException {

    public TokenKeyException() {
        super(ConstansPayment.TOKEN_KEY_EXISTS.getMessage());
    }
}
