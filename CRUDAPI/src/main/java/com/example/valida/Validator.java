package com.example.valida;

import com.example.check.CheckSum;

import com.example.config.YamlConfig;
import com.example.model.User;

public class Validator {
    public static boolean checkSum(User user){
        //SHA256 (mobile + bankCode + accountNo + payDate + debitAmount + respCode +
        //traceTransfer + messageType + privateKey)
        String dataCheckSum = user.getMobile()+ user.getBankCode()+ user.getAccountNo()+ user.getPayDate()+ user.getDebitAmount()+ user.getRespCode()
                + user.getTraceTransfer()+ user.getMessageType() + YamlConfig.getYamlConfig();

        String checkSum = CheckSum.sha256Base64(dataCheckSum);

        return checkSum.equals(user.getCheckSum());


    }
}
