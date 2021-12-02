package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class ApiBlankException extends RuntimeException {

    public ApiBlankException() {
        super(ConstansPayment.API_ID_BLANK.getMessage());
    }
}
