package com.example.producer.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseDTO {
    private int code;
    private String message;
    private String responseId;
    private String checkSum;
    private String addValue;

}