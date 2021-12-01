package com.example.consumer.comstans;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusMessage {
    CALL_API_ERROR(9, "Call api fail:"),

    SUCCESS(0, "Successfull"),

    QUESTIONABLE_TRANSACTIONS(8, "Giao dịch nghi vấn");

    private final int value;
    private final String message;

}
