package com.example.producer.dto;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class PaymentDTO {

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

    private String addValue = "{\"payMethod\":\"01\",\"payMethodMMS\":1}";

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "tokenKey='" + tokenKey + '\'' +
                ", apiID='" + apiID + '\'' +
                ", mobile='" + mobile + '\'' +
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
                ", bankCode='" + bankCode + '\'' +
                ", userName='" + userName + '\'' +
                ", realAmount='" + realAmount + '\'' +
                ", promotionCode='" + promotionCode + '\'' +
                ", addValue='" + addValue + '\'' +
                '}';
    }
}




//    public boolean isValidPayment() {
//
//        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        df.setLenient(false);
//        if (StringUtils.isEmpty(this.apiID) || StringUtils.isEmpty(this.orderCode)) {
//            log.info("ApiId or orderCode is empty");
//            return false;
//        } else if (!DateUtil.tryParse(df, this.payDate)) {
//            log.info("payDate is illegal");
//            return false;
//        } else if (this.realAmount > this.debitAmount) {
//            log.info("realAmount is bigger than debitAmount");
//            return false;
//        } else if (this.realAmount == this.debitAmount && StringUtils.isEmpty(this.promotionCode)) {
//            log.info("promotionCode is blank");
//            return false;
//        }
//        return true;
//    }
//
//    public boolean isInValidPayment(){
//        return !isValidPayment();
//    }
//

