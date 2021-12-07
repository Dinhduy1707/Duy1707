package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class TimeOutException extends RuntimeException {
    public TimeOutException() {
        super(ConstansPayment.MAINTENANCE_SYSTEM.getMessage());
    }
}