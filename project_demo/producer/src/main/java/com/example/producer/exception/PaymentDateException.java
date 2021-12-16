package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class PaymentDateException extends RuntimeException {

    public PaymentDateException() {
        super(ConstantPayment.PAY_DATE_WRONG_FORMAT.getMessage());
    }
}
