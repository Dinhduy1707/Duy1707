package com.example.entity;

import com.example.validate.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
import java.lang.reflect.Field;

@Data
@AllArgsConstructor
public class Payment implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
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

    private double debitAmount;
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

    public boolean isNull() {
        try {
            for (Field f : getClass().getDeclaredFields())
                if (f.get(this) == null || f.get(this).equals("") || this.debitAmount == 0L)
                    return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }


    public String getJsonData() {
        return "Payment{" +
                "id=" + id +
                ", tokenKey='" + tokenKey + '\'' +
                ", apiID='" + apiID + '\'' +
                ", mobile='" + mobile + '\'' +
                ", bankCode='" + bankCode + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", payDate='" + payDate + '\'' +
                ", addtionalData='" + addtionalData + '\'' +
                ", debitAmount=" + debitAmount +
                ", respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                ", traceTransfer='" + traceTransfer + '\'' +
                ", messageType='" + messageType + '\'' +
                ", checkSum='" + checkSum + '\'' +
                ", oderCode='" + oderCode + '\'' +
                ", userName='" + userName + '\'' +
                ", realAmount=" + realAmount +
                ", promotionCode='" + promotionCode + '\'' +
                ", addValue='" + addValue + '\'' +
                '}';
    }
}
