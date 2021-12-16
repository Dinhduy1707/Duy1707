package com.example.consumer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_pay1")
@Builder
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(nullable = false, unique = true)
    private long id;

    @Column(name = "created_date", columnDefinition = "timestamp")
    private ZonedDateTime createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = ZonedDateTime.now();
    }

    private String tokenKey;

    private String apiID;

    private String mobile;

    @Column(columnDefinition = "varchar(255) default '970445'")
    private String bankCode;

    private String accountNo;

    private String payDate;

    private String addtionalData;

    private double debitAmount;

    private String respCode;

    private String respDesc;

    private String traceTransfer;

    @Column(columnDefinition = "varchar(2) default '1'")
    private String messageType;

    private String checkSum;

    private String orderCode;

    private String userName;

    private String realAmount;

    private String promotionCode;

    @Column(columnDefinition = "varchar(255) default '{\\\"payMethod\\\":\\\"01\\\",\\\"payMethodMMS\\\":1}'")
    private String addValue;

    @Override
    public String toString() {
        return "Pay{" +
                "id=" + id +
               // ", createdDate=" + createdDate +
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
                ", orderCode='" + orderCode + '\'' +
                ", userName='" + userName + '\'' +
                ", realAmount='" + realAmount + '\'' +
                ", promotionCode='" + promotionCode + '\'' +
                ", addValue='" + addValue + '\'' +
                '}';
    }
}
