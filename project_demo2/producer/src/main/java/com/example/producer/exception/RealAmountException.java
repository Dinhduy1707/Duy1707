package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class RealAmountException extends RuntimeException {

    public RealAmountException() {
        super(ConstansPayment.REAL_AMOUNT_MISTAKE.getMessage());
    }
}
