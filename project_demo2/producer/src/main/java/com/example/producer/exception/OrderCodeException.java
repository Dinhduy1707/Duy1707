package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class OrderCodeException extends RuntimeException {

    public OrderCodeException() {
        super(ConstansPayment.ORDER_CODE_BLANK.getMessage());
    }
}
