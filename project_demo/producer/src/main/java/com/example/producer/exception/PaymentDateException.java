package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class PaymentDateException extends RuntimeException {

    public PaymentDateException() {
        super(ConstansPayment.PAY_DATE_WRONG_FORMAT.getMessage());
    }
}
