package com.example.producer.config.rabbitMQ;

import com.example.producer.exception.ChannelException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
@Log4j2
public class ChannelFactory implements PooledObjectFactory<Channel> {
    private final Connection connection;

    public ChannelFactory() {
        try {
            ConfigRabbitMq configRabbitMq = ConfigRabbitMq.getConfigRabbitMq();
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost(configRabbitMq.getHOST());
            connection = factory.newConnection();
        } catch (Exception e) {
            throw new ChannelException("Connection  fail ", e);
        }
    }


    public PooledObject<Channel> makeObject() throws Exception {

        return new DefaultPooledObject<Channel>(connection.createChannel());
    }

    public void destroyObject(PooledObject<Channel> pooledObject) throws Exception {
        final Channel channel = pooledObject.getObject();
        if (channel.isOpen()) {
            try {
                channel.close();
            } catch (IOException | TimeoutException e) {
                log.error(e.getMessage());
            }
        }
    }


    public boolean validateObject(PooledObject<Channel> pooledObject) {
        final Channel channel = pooledObject.getObject();
        return channel.isOpen();
    }

    public void activateObject(PooledObject<Channel> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<Channel> p) throws Exception {

    }
}


