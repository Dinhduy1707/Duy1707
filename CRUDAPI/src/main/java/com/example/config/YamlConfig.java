package com.example.config;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.example.check.GsonCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class YamlConfig {
    private static final Logger LOG = LogManager.getLogger(YamlConfig.class);


    // public static void main(String[] args) {
  public static YamlConfig  getYamlConfig() {
        try {


            YamlReader reader = new YamlReader(new FileReader("C://Users//Dinh Duy//Downloads//CRUDAPI//src//main//resources//application.yml"));
            Object object = reader.read();
            LOG.info("Object {} :", GsonCustom.getInstance().toJson(object));
            Map map = (Map) object;
            LOG.info(map.get("privateKey"));

        } catch (Exception e) {
            LOG.error("Error not foud file ", e);
        }
        return null;
    }
}








