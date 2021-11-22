package com.example.constans;

public enum ResponseCode {
    SUCCESS("00", "SUCCESS"),
    NOT_NULL("01", "NOT_NULL"),
    BANK_CODE_NOT_NULL("02", "BANK_CODE_NOT_NULL"),
    INSERT_DATA_FAIL("04", "INSERT_DATA_FAIL");


    private final String responseCode;
    private final String responseMessage;


    ResponseCode(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;

    }

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }
}
