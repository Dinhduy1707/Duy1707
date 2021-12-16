package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class RealAmountException extends RuntimeException {

    public RealAmountException() {
        super(ConstantPayment.REAL_AMOUNT_MISTAKE.getMessage());
    }
}
