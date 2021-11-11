package com.example.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private static final Logger LOG = LogManager.getLogger(Property.class);

    private static Properties instance;

    private Property() {
    }

    public static Properties getInstance() {
        if (instance == null) {
            synchronized (Property.class) {
                if (instance == null) {
                    instance = new Properties();
                    try {
                        instance.load(new FileInputStream("C://Users//Dinh Duy//OneDrive//Máy tính//Duy1707//CRUDAPI//src//main//resources//dev.properties"));
                        LOG.info("Load file dev.properties success");
                    } catch (IOException e) {

                        LOG.error("Error load file dev.properties", e);
                    }
                }
            }
        }
        return instance;
    }

}
