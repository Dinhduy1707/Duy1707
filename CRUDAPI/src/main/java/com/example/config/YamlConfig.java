package com.example.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.example.common.GsonCustom;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.FileReader;
import java.util.List;

@Slf4j
@Configuration
@ConfigurationProperties(prefix = "application.properties")
public class YamlConfig {

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

            log.info("Object {} :", GsonCustom.getInstance().toJson(object));
            return object;
        } catch (Exception e) {
            log.error("Error not foud file ", e);
        }
        return null;
    }

    public static void main(String[] args) {
        getYamlConfig();
    }
}








