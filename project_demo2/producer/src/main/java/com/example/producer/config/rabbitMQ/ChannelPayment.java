package com.example.producer.config.rabbitMQ;

import com.example.producer.constant.ConstansRabbitMQ;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class ChannelPayment {

    @Bean
    public Channel initChannelRabbit() {
        Channel channel = null;
        log.info("Init channel");
        try {
            channel = ChannelPool.getChannelPool().getChannel();
            channel.exchangeDeclare(ConstansRabbitMQ.DIRECT_EXCHANGE_PAYMENT, ConstansRabbitMQ.RABBIT_EXCHANGE_DIRECT_TYPE);
            channel.queueDeclare(ConstansRabbitMQ.QUEUE_DATA_PAYMENT, false, false, false, null);
            channel.queueBind(ConstansRabbitMQ.QUEUE_DATA_PAYMENT, ConstansRabbitMQ.DIRECT_EXCHANGE_PAYMENT, ConstansRabbitMQ.KEY_PAYMENT);
            return channel;
        }catch (Exception e){
            log.error("Error! Rabbit setup error ex {}:",e.getMessage());
            return channel;
        }
    }
}