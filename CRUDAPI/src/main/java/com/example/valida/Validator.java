package com.example.valida;

import com.example.check.CheckSum;

import com.example.config.Bank;
import com.example.config.YamlConfig;
import com.example.model.User;

import java.util.List;

public class Validator {

    public static boolean checkSum(User user) {

        String dataCheckSum = user.getMobile() + user.getBankCode() + user.getAccountNo() + user.getPayDate() + user.getDebitAmount() + user.getRespCode()
                + user.getTraceTransfer() + user.getMessageType() + YamlConfig.getPrivateKey(user.getBankCode());
        String checkSum = CheckSum.sha256Base64(dataCheckSum);
        return checkSum.equals(user.getCheckSum());
    }
    public static void main(String[] args) {
        String str = "0386566020" + "BIDV" + "0001100014211002" + "20200929112923" + "11200" + "00" + "FT19310RH6P1" + "1" + YamlConfig.getPrivateKey("BIDV");
        System.out.println(CheckSum.sha256Base64(str));
    }


}
