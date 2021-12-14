package com.example.producer.config.rabbitMQ;

import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Log4j2
@Configuration
public class ChannelPayment {
    private final ChannelPool channelPool;

    public ChannelPayment(ChannelPool channelPool) {
        this.channelPool = channelPool;

    }
   @Bean
    public Channel initChannelRabbit() {

        log.info("Init channel");
        Channel channel = channelPool.getChannel();
        try {
            channel.exchangeDeclare(ConfigRabbitMq.getConfigRabbitMq().getDIRECT_EXCHANGE_PAYMENT(), ConfigRabbitMq.getConfigRabbitMq().getRABBIT_EXCHANGE_DIRECT_TYPE());
            channel.queueDeclare(ConfigRabbitMq.getConfigRabbitMq().getQUEUE_DATA_PAYMENT(), true, false, false, null);
            channel.queueBind(ConfigRabbitMq.getConfigRabbitMq().getQUEUE_DATA_PAYMENT(), ConfigRabbitMq.getConfigRabbitMq().getDIRECT_EXCHANGE_PAYMENT(), ConfigRabbitMq.getConfigRabbitMq().getKEY_PAYMENT());
            return channel;
        } catch (Exception e) {
            log.error("Error! Rabbit setup error ex {}:", e);
            return channel;
        }
    }

}
