package com.example.producer.service.Impl;

import com.example.producer.config.rabbitMQ.ChannelPool;
import com.example.producer.constant.ConstansRabbitMQ;
import com.example.producer.dto.PaymentDTO;
import com.example.producer.dto.ResultDTO;
import com.example.producer.service.RabbitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
@Log4j2
@AllArgsConstructor
public class RabbitServiceImpl implements RabbitService {
    private final Channel channel;
    private ObjectMapper mapper;

    @Override
    public ResultDTO sendDataPayment(PaymentDTO paymentDTO) {
        log.info("Start send data");
        try {
            log.info("Create RPC connection and send the object to consumer.");
            String corrId = UUID.randomUUID().toString();
            String replyQueueName = channel.queueDeclare().getQueue();
            AMQP.BasicProperties props = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(corrId)
                    .replyTo(replyQueueName)
                    .build();
            // byte[] data = mapper.writeValueAsBytes(object);
            channel.basicPublish(ConstansRabbitMQ.DIRECT_EXCHANGE_PAYMENT, ConstansRabbitMQ.KEY_PAYMENT, props,
                    mapper.writeValueAsString(paymentDTO).getBytes(StandardCharsets.UTF_8));
            log.info("Send request dataa to ques success");

            return listenToReply(channel, corrId, replyQueueName);
        } catch (Exception e) {
            log.error("Create RPC RabbitMQ failed! " + e);
        } finally {
            if (channel != null)
                ChannelPool.getChannelPool().returnChannel(channel);
                log.info("Finish send and receive data payment to queue,tokenkey: {}", paymentDTO.getTokenKey());

            }
            return null;
        }

    private ResultDTO listenToReply(Channel channel, String corrId, String replyQueueName) throws IOException, InterruptedException {
        log.info("Waiting reply to consumer............");
        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
        String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response.offer(new String(delivery.getBody(), "UTF-8"));
            }
        }, consumerTag -> {
        });
        String dataReply = response.poll(60, TimeUnit.SECONDS);
        ResultDTO responsePartnerDTO = mapper.readValue(dataReply, ResultDTO.class);
        log.info("Response payment consumer data:{}", responsePartnerDTO.toString());
        channel.basicCancel(ctag);
        return responsePartnerDTO;
    }
}
