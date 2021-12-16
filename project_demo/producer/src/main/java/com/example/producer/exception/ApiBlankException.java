package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class ApiBlankException extends RuntimeException {

    public ApiBlankException() {

        super(ConstantPayment.API_ID_BLANK.getMessage());
    }
}
