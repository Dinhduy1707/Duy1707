package com.example.producer.config.rabbitMQ;

import com.example.producer.constant.RabbitMQConst;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
@RequiredArgsConstructor
public class ChannelPayment {

    @Autowired
    private ChannelPool channelPool;

   @Bean
    public Channel initChannelRabbit() {
          Channel channel = null ;
        log.info("Init channel");
        try {

            channel = channelPool.getChannel();
            channel.exchangeDeclare(RabbitMQConst.DIRECT_EXCHANGE_PAYMENT, RabbitMQConst.RABBIT_EXCHANGE_DIRECT_TYPE);
            channel.queueDeclare(RabbitMQConst.QUEUE_DATA_PAYMENT, true, false, false, null);
            channel.queueBind(RabbitMQConst.QUEUE_DATA_PAYMENT, RabbitMQConst.DIRECT_EXCHANGE_PAYMENT, RabbitMQConst.KEY_PAYMENT);
            return channel;
        } catch (Exception e) {
            log.error("Error! Rabbit setup error ex {}:", e);
            return channel;
        }
    }

}
