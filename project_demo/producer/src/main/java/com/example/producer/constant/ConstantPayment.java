package com.example.producer.constant;

public enum ConstantPayment {
    TOKEN_KEY_EXISTS(01, "tokenKey exists"),
    API_ID_BLANK(02, "ApiID not blank"),
    PAY_DATE_WRONG_FORMAT(03, "payDate format yyyyMMddHHmmss"),
    ORDER_CODE_BLANK(04, "orderCode not blank"),
    REAL_AMOUNT_MISTAKE(05, "realAmount cannot be greater than debitAmount"),
    PROMOTION_CODE_NULL(06, "promotionCode not null"),
    MAINTENANCE_SYSTEM(96, "Hệ thống bảo trì");
    private final int value;
    private final String message;

    ConstantPayment(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}

