package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class OrderCodeException extends RuntimeException {

    public OrderCodeException() {
        super(ConstantPayment.ORDER_CODE_BLANK.getMessage());
    }
}
