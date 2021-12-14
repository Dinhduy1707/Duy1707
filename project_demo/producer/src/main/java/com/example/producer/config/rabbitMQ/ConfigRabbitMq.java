package com.example.producer.config.rabbitMQ;

import com.example.producer.config.file.RealFileConfig;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.Properties;

@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConfigRabbitMq {
    private Properties properties;
    private static final String PATH_QUEUE = "C:\\Users\\Dinh Duy\\OneDrive\\Máy tính\\project_demo\\producer\\src\\configfile\\queue.properties";
    private final String QUEUE_DATA_PAYMENT;
    private final String DIRECT_EXCHANGE_PAYMENT;
    private final String HOST;
    private final String MAX_TOTAL;
    private final String RABBIT_EXCHANGE_DIRECT_TYPE;
    private final String KEY_PAYMENT;


    private ConfigRabbitMq() {
        this.properties = RealFileConfig.fileConfig().readFile(PATH_QUEUE);
        this.QUEUE_DATA_PAYMENT = properties.getProperty("QUEUE_DATA_PAYMENT");
        this.DIRECT_EXCHANGE_PAYMENT = properties.getProperty("DIRECT_EXCHANGE_PAYMENT");
        this.HOST = properties.getProperty("HOST");
        this.MAX_TOTAL = properties.getProperty("MAX_TOTAL");
        this.RABBIT_EXCHANGE_DIRECT_TYPE = properties.getProperty("RABBIT_EXCHANGE_DIRECT_TYPE");
        this.KEY_PAYMENT = properties.getProperty("KEY_PAYMENT");
    }
    @Bean
    @Scope("singleton")
    public static ConfigRabbitMq getConfigRabbitMq() {
        return new ConfigRabbitMq();
    }
}
