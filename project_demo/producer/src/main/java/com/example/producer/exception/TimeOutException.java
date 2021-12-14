package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class TimeOutException extends RuntimeException {
    public TimeOutException() {
        super(ConstantPayment.MAINTENANCE_SYSTEM.getMessage());
    }
}