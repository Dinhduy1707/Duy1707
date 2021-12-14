package com.example.producer.config.rabbitMQ;

import com.example.producer.config.file.RealFileConfig;
import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Data
@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ChannelPoolConfig {
    private final Properties props;
    private static final String DIRECTORY_PATH_POOL = "C:\\Users\\Dinh Duy\\OneDrive\\Máy tính\\project_demo\\producer\\src\\configfile\\pool.properties";
    private final int maxTotal;
    private final int maxIdle;
    private final int minIdle;



    private ChannelPoolConfig() {
        this.props = RealFileConfig.fileConfig().readFile(DIRECTORY_PATH_POOL);
        this.maxTotal = Integer.parseInt(props.getProperty("pool.max-total"));
        this.maxIdle = Integer.parseInt(props.getProperty("pool.max-idle"));
        this.minIdle = Integer.parseInt(props.getProperty("pool.min-idle"));
    }

    @Bean
    @Scope("singleton")
    public static ChannelPoolConfig getInstance() {
        return new ChannelPoolConfig();
    }




}
