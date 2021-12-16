package com.example.consumer.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class PaymentDTO {
    private  Long  id ;

    private String tokenKey;

    private String apiID;

    private String mobile;

    private String accountNo;

    private String payDate;

    private String addtionalData;

    private double debitAmount;

    private String respCode;

    private String respDesc;

    private String traceTransfer;

    private String messageType = "1";

    private String checkSum;

    private String orderCode;

    private String bankCode = "970445";

    private String userName;

    private String realAmount;

    private String promotionCode;

    private String addValue ="{\"payMethod\":\"01\",\"payMethodMMS\":1}";}