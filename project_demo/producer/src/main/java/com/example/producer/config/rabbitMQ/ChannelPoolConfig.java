package com.example.producer.config.rabbitMQ;

import com.example.producer.config.file.RealFileConfig;
import lombok.Data;

import java.util.Properties;

@Data
public class ChannelPoolConfig {
        private Properties props;
        private static final String DIRECTORY_PATH_POOL = "C:\\Users\\Dinh Duy\\OneDrive\\Máy tính\\project_demo2\\producer\\src\\configfile\\pool.properties";
        private final int maxTotal;
        private final int maxIdle;
        private final int minIdle;

        private ChannelPoolConfig() {
            this.props = RealFileConfig.getInstance().readFile(DIRECTORY_PATH_POOL);
            this.maxTotal = Integer.parseInt(props.getProperty("pool.max-total"));
            this.maxIdle = Integer.parseInt(props.getProperty("pool.max-idle"));
            this.minIdle = Integer.parseInt(props.getProperty("pool.min-idle"));
        }

        public static ChannelPoolConfig getInstance() {
            return SingletonHelper.POOL_CONFIG;
        }

        private static final class SingletonHelper {
            private static final ChannelPoolConfig POOL_CONFIG = new ChannelPoolConfig();
        }

}
