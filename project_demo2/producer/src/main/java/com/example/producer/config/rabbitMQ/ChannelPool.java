package com.example.producer.config.rabbitMQ;

import com.example.producer.constant.ConstansRabbitMQ;
import com.example.producer.exception.ChannelException;
import com.rabbitmq.client.Channel;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.util.NoSuchElementException;

public class ChannelPool implements Cloneable{
    private GenericObjectPool<Channel> internalPool;
    public static GenericObjectPoolConfig defaultConfig;

    static {
        defaultConfig = new GenericObjectPoolConfig();
        defaultConfig.setMaxTotal(ConstansRabbitMQ.MAX_TOTAL);
        defaultConfig.setBlockWhenExhausted(false);
    }

    private static class SingletonHelper {
        private static final ChannelPool channelPool = new ChannelPool();
    }
    public ChannelPool() {
        this(defaultConfig, new ChannelFactory());
    }
    public static ChannelPool getChannelPool() {
        return SingletonHelper.channelPool;
    }
    public ChannelPool(final GenericObjectPoolConfig poolConfig, ChannelFactory factory) {
        if (this.internalPool != null) {
            try {
                closeInternalPool();
            } catch (Exception e) {
            }
        }

        this.internalPool = new GenericObjectPool<Channel>(factory, poolConfig);
    }

    private void closeInternalPool() {
        try {
            internalPool.close();
        } catch (Exception e) {
            throw new ChannelException("Could not destroy the pool", e);
        }
    }

    public void returnChannel(Channel channel) {
        try {
            if (channel.isOpen()) {
                internalPool.returnObject(channel);
            } else {
                internalPool.invalidateObject(channel);
            }
        } catch (Exception e) {
            throw new ChannelException("Could not return the resource to the pool", e);
        }
    }

    public Channel getChannel() {
        try {
            return internalPool.borrowObject();
        } catch (NoSuchElementException nse) {
            if (null == nse.getCause()) {
                throw new ChannelException("Could not get a resource since the pool is exhausted", nse);
            }
            throw new ChannelException("Could not get a resource from the pool", nse);
        } catch (Exception e) {
            throw new ChannelException("Could not get a resource from the pool", e);
        }
    }
}
