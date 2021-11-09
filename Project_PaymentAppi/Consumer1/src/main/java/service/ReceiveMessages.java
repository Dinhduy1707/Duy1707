package service;

//import Service.UserService;
//import Service.UserServiceIplm;
import bean.User;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import common.ChannelPool;
//import common.CommonPool;
import common.CommonPool;
import common.GsonCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import java.io.IOException;
public class ReceiveMessages implements  Runnable {

    private static final Logger LOG = LogManager.getLogger(ReceiveMessages.class);
    final static UserServiceIplm  userServiceIplm = new UserServiceIplm();
    private Channel channel;

    public ReceiveMessages() { }

    @Override
    public void run() {
        try {
            channel = CommonPool.basicChannelPool.getChannel();
            channel.queueDeclare("ott_message", true, false, false, null);
            getMessage(channel);
        } catch (Exception e) {
            LOG.error("Error get message from queue: "+e.getMessage());
        }
    }

    private void getMessage(Channel channel) throws IOException {
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {

            String message = new String(delivery.getBody(), "UTF-8");
            LOG.debug("queueName: "+ ChannelPool.queueName+" message: " + message);
            try {
                User user = GsonCustom.getInstance().fromJson(message, User.class);
                userServiceIplm.insertUser(user);
                System.out.println("insert data: "+user.getApiID());
                ThreadContext.clearAll();
            } catch (Exception e) {
                LOG.error("Error getMessage: ", e);
                e.printStackTrace();
            }
            ThreadContext.clearAll();
        };
        channel.basicConsume("ott_message", true, deliverCallback, consumerTag -> {
        });
    }
}

