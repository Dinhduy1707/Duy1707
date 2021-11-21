package com.example.valida;

import com.example.check.CheckSum;
import com.example.config.YamlConfig;
import com.example.entity.Payment;


public class Validator {


    public static boolean checkSum(Payment payment) {

        String dataCheckSum = payment.getMobile() + payment.getBankCode() + payment.getAccountNo() + payment.getPayDate() + payment.getDebitAmount() + payment.getRespCode()
                + payment.getTraceTransfer() + payment.getMessageType() + YamlConfig.getPrivateKey(payment.getBankCode());

        String checkSum = CheckSum.sha256Base64(dataCheckSum);

        return checkSum.equals(payment.getCheckSum());

    }

    public static void main(String[] args) {
        String str = "0386566020" + "BIDV" + "0001100014211002" + "20200929112923" + "11200" + "00" + "FT19310RH6P1" + "1" +
                YamlConfig.getPrivateKey("BIDV");
        System.out.println(CheckSum.sha256Base64(str));
    }


}
