package com.example.producer.exception;

import com.example.producer.constant.ConstansPayment;

public class PromotionCodeException  extends RuntimeException {

    public PromotionCodeException() {
        super(ConstansPayment.PROMOTION_CODE_NULL.getMessage());
    }
}
