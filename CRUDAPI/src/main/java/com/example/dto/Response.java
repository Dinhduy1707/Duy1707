package com.example.dto;

import com.example.constans.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class Response {
    private String code;
    private String message;

    public static Response to(ResponseCode responseCode) {
        return Response.builder()
                .code(responseCode.getResponseCode())
                .message(responseCode.getResponseMessage())
                .build();
    }
}
