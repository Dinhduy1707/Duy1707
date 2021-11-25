package service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import common.ChannelCommon;
import common.CommonPool;
import common.GsonCustom;
import lombok.extern.log4j.Log4j2;
import model.Payment;
import org.apache.logging.log4j.ThreadContext;

import java.io.IOException;


@Log4j2
public class ReceiveMessages implements Runnable {

    final static PaymentService userServiceIplm = new PaymentService();

    private Channel channel;

    public ReceiveMessages() {
    }

    @Override
    public void run() {
        try {

            channel = CommonPool.basicChannelPool.getChannel();
            channel.queueDeclare(ChannelCommon.queueName, true, false, false, null);
            getMessage(channel);
        } catch (Exception e) {
            log.error("Error get message from queue: " + e.getMessage());
        }
    }

    private void getMessage(Channel channel) throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), "UTF-8");
            log.debug("queueName: " + ChannelCommon.queueName + " message: " + message);
            try {
                Payment payment = GsonCustom.getInstance().fromJson(message, Payment.class);
                userServiceIplm.insertUser(payment);
                System.out.println("insert data: " + payment.getId());
                ThreadContext.clearAll();
            } catch (Exception e) {
                log.error("Error getMessage: ", e);
                e.printStackTrace();
            }
            ThreadContext.clearAll();
        };
        channel.basicConsume(ChannelCommon.queueName, true, deliverCallback, consumerTag -> {
        });
    }
}

