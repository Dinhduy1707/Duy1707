package com.example.entity;

import com.example.valida.NotBlank;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Payment")
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    @NotBlank
    private String tokenKey;
    @NotBlank
    private String apiID;
    @NotBlank
    private String mobile;
    @NotBlank
    private String bankCode;
    @NotBlank
    private String accountNo;
    @NotBlank
    private String payDate;
    @NotBlank
    private String addtionalData;

    private int debitAmount;
    @NotBlank
    private String respCode;
    @NotBlank
    private String respDesc;
    @NotBlank
    private String traceTransfer;
    @NotBlank
    private String messageType;
    @NotBlank
    private String checkSum;
    @NotBlank
    private String oderCode;
    @NotBlank
    private String userName;
    @NotBlank
    private String realAmount;
    @NotBlank
    private String promotionCode;
    @NotBlank
    private String addValue;

    public Payment() {
    }
}
