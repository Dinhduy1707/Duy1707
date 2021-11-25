package model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Payment {
    private int id;
    @NotNull
    private String apiID;
    @NotNull
    private String tokenKey;
    @NotNull
    private String bankCode;
    @NotNull
    private String mobile = "970445";
    @NotNull
    private String accountNo;
    @NotNull
    private String payDate;
    @NotNull
    private String addtionalData;
    @NotNull
    private String debitAmount;
    @NotNull
    private String respCode;
    @NotNull
    private String respDesc;
    @NotNull
    private String traceTransfer;
    @NotNull
    private String messageType;
    @NotNull
    private String checkSum;
    @NotNull
    private String oderCode;
    @NotNull

    private String userName;
    @NotNull

    private String realAmount;
    @NotNull
    private String promotionCode;
    @NotNull
    private String addValue;

    @NotNull


    public Payment() {
    }


}
