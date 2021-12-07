package com.example.producer.config.file;

import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class RealFileConfig {
    private RealFileConfig() {

    }
    public Properties readFile(String path) {
        log.info("Begin real file ");
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(path);
            properties.load(inputStream);
            log.info("Read file success");
        } catch (FileNotFoundException e) {
            log.error("FileNotFound", e);
        } catch (IOException e) {
            log.error("IOException ", e);
        }
        log.info("End ");
        return properties;
    }

    public static RealFileConfig getInstance() {
        return Instance.REAL_FILE_CONFIG;
    }

    private static final class Instance {
        private static final RealFileConfig REAL_FILE_CONFIG = new RealFileConfig();
    }

}
