package com.example.producer.config.rabbitMQ;

import com.example.producer.constant.RabbitMQConst;
import com.example.producer.exception.ChannelException;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Log4j2
@Component

public class ChannelPool implements Cloneable {
    private GenericObjectPool<Channel> internalPool;
    private static GenericObjectPoolConfig defaultConfig;

    static {
        defaultConfig = new GenericObjectPoolConfig();
        defaultConfig.setMaxTotal(RabbitMQConst.MAX_TOTAL);
        defaultConfig.setMinIdle(RabbitMQConst.MIN_IDLE);
        defaultConfig.setMaxIdle(RabbitMQConst.MAX_IDLE);
       defaultConfig.setBlockWhenExhausted(false);
    }

    public ChannelPool() {
        this(defaultConfig, new ChannelFactory());
    }
    @Bean
    private static ChannelPool getChannelPool() {
        return new ChannelPool();
    }


    public ChannelPool(final GenericObjectPoolConfig poolConfig, ChannelFactory factory) {

        this.internalPool = new GenericObjectPool<Channel>(factory, poolConfig);
    }

    private void closeInternalPool() {
        if (internalPool != null) {
            internalPool.close();
        }
    }

    public void returnChannel(Channel channel) {
        log.info("Start returnChannel");
        try {
            if (channel.isOpen()) {
                internalPool.returnObject(channel);
            } else {
                internalPool.invalidateObject(channel);
            }
        } catch (Exception e) {
            throw new ChannelException("Error", e);
        }
        log.info("End returnChannel");
    }

    public Channel getChannel() {
        log.info("Start getChannel");
        try {
            Channel channel = internalPool.borrowObject();
            log.info("End getChannel");
            return channel;
        } catch (Exception e) {
            throw new ChannelException("Could not get a resource from the pool", e);
        }
    }

}
