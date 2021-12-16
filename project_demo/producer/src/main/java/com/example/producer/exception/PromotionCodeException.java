package com.example.producer.exception;

import com.example.producer.constant.ConstantPayment;

public class PromotionCodeException  extends RuntimeException {

    public PromotionCodeException() {
        super(ConstantPayment.PROMOTION_CODE_NULL.getMessage());
    }
}
