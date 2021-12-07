package com.example.consumer.config.rabbitMQ;

import com.example.consumer.common.CommonRabbit;
import com.rabbitmq.client.Channel;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Log4j2
public class ChannelPayment {


    @Bean
    public Channel initChannelRabbit() {
        Channel channel = null;
        log.info("Begin channel Init ");
        try {
            channel = ChannelPool.getChannelPool().getChannel();
            channel.exchangeDeclare(CommonRabbit.DIRECT_EXCHANGE_PAYMENT, CommonRabbit.RABBIT_EXCHANGE_DIRECT_TYPE);
            channel.queueDeclare(CommonRabbit.QUEUE_DATA_PAYMENT, true, false, false, null);
            channel.queueBind(CommonRabbit.QUEUE_DATA_PAYMENT, CommonRabbit.DIRECT_EXCHANGE_PAYMENT, CommonRabbit.KEY_PAYMENT);
            log.info("Succues");;
            return channel;

        }catch (Exception ex){
            log.error("Error rabbit fail {}:",ex.getMessage());
            return channel;
        }
    }

}
