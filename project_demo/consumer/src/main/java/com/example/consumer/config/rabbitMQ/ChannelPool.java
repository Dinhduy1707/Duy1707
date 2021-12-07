package com.example.consumer.config.rabbitMQ;

import com.example.consumer.common.CommonRabbit;
import com.example.consumer.exception.ChannelException;
import com.rabbitmq.client.Channel;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ChannelPool implements Cloneable {
    private GenericObjectPool<Channel> internalPool;
    private static GenericObjectPoolConfig defaultConfig;

    static {
        defaultConfig = new GenericObjectPoolConfig();
        defaultConfig.setMaxTotal(CommonRabbit.MAX_TOTAL);
        defaultConfig.setBlockWhenExhausted(false);
    }

    public ChannelPool() {
        this(defaultConfig, new ChannelFactory());
    }

    private static class SingletonHelper {
        private static final ChannelPool channelPool = new ChannelPool();
    }

    public static ChannelPool getChannelPool() {
        return SingletonHelper.channelPool;
    }

    public ChannelPool(final GenericObjectPoolConfig poolConfig, ChannelFactory factory) {
        if (null != this.internalPool) {
            closeInternalPool();
        }

        this.internalPool = new GenericObjectPool<Channel>(factory, poolConfig);
    }

    private void closeInternalPool() {
        try {
            internalPool.close();
        } catch (Exception e) {

        }
        throw new ChannelException("Close pool");
    }

    public void returnChannel(Channel channel) {
        try {
            if (channel.isOpen()) {
                internalPool.returnObject(channel);
            } else {
                internalPool.invalidateObject(channel);
            }
        } catch (Exception e) {
            throw new ChannelException("Could not return  pool");
        }
    }

    public Channel getChannel() {
        try {
            return internalPool.borrowObject();
        } catch (Exception e) {
            throw new ChannelException("Could not get  pool" );
        }
    }
}
