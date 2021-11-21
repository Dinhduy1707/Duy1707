package com.example.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.example.common.GsonCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.util.List;

public class YamlConfig {
    private static final Logger LOG = LogManager.getLogger(YamlConfig.class);
    static List<BankConfig> banks = YamlConfig.getYamlConfig().getBanks();


    public static String getPrivateKey(String bankCode) {
        if (null == banks || banks.isEmpty()) return null;
        for (BankConfig bank : banks) {
            if (bank.getBankCode().equalsIgnoreCase(bankCode)) {
                return bank.getPrivateKey();
            }
        }
        return null;
    }

    public static Banks getYamlConfig() {
        try {
            YamlReader reader = new YamlReader(
                    new FileReader("C:\\Users\\Dinh Duy\\OneDrive\\Máy tính\\Duy1707\\CRUDAPI\\src\\main\\resources\\application.yml"));
            Banks object = reader.read(Banks.class);

            LOG.info("Object {} :", GsonCustom.getInstance().toJson(object));
            return object;
        } catch (Exception e) {
            LOG.error("Error not foud file ", e);
        }
        return null;
    }

    public static void main(String[] args) {
        getYamlConfig();
    }
}








