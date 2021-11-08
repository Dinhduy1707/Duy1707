package com.example.valida;

import com.example.check.CheckSum;

import com.example.config.YamlConfig;
import com.example.model.Product;

public class Validator {
    public static boolean checkSum(Product product){
        //SHA256 (mobile + bankCode + accountNo + payDate + debitAmount + respCode +
        //traceTransfer + messageType + privateKey)
        String dataCheckSum = product.getMobile()+product.getBankCode()+product.getAccountNo()+product.getPayDate()+product.getDebitAmount()+product.getRespCode()
                +product.getTraceTransfer()+product.getMessageType() + YamlConfig.getYamlConfig();

        String checkSum = CheckSum.sha256Base64(dataCheckSum);

        return checkSum.equals(product.getCheckSum());


    }
}
