package com.example.producer.config.file;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
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


    @Bean
    @Scope("singleton")
    public static RealFileConfig fileConfig() {
        return new    RealFileConfig();
}

}
