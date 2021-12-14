package com.example.consumer.starup;

import com.example.consumer.common.CommonRabbit;
import com.example.consumer.config.rabbitMQ.ChannelPool;
import com.example.consumer.dto.PaymentDTO;
import com.example.consumer.dto.ResponseDTO;
import com.example.consumer.entity.Payment;
import com.example.consumer.request.RequestApi;
import com.example.consumer.service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

@Configuration
@AllArgsConstructor
@Log4j2
public class Consumer {

    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final RequestApi requestApiPartner;
    private final Channel channel;
    private final PaymentService paymentService;

    @Bean
    public void receiveDataFromQueueSaveDBAndReply() {
        try {
            channel.basicQos(1);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                log.info("Start receiving messages ...");
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                        .Builder()
                        .correlationId(delivery.getProperties().getCorrelationId())
                        .build();

                String message = new String(delivery.getBody(), "UTF-8");
                PaymentDTO payDTO = objectMapper.readValue(message, PaymentDTO.class);
                log.info("Data from queue,data:{}", payDTO.toString());
                Payment pay = modelMapper.map(payDTO, Payment.class);

                paymentService.saveData(pay);
                log.info("Save data pay success", pay.toString());

                ResponseDTO responsePartnerDTO = requestApiPartner.postRequestPartner(payDTO);
                channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps,
                        objectMapper.writeValueAsString(responsePartnerDTO).getBytes(StandardCharsets.UTF_8));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                log.info("Reply message to queue payment success ,data message:{}", responsePartnerDTO.toString());
            };
            channel.basicConsume(CommonRabbit.QUEUE_DATA_PAYMENT, false, deliverCallback, (consumerTag -> {
            }));
        } catch (Exception ex) {
            log.error("Exception ex:{}", ex);
        } finally {
            if (channel != null) ChannelPool.getChannelPool().returnChannel(channel);
        }
    }
}

