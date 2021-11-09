package syncer;

import bean.User;
import com.rabbitmq.client.Channel;
import common.ChannelPool;
import common.CommonPool;
import common.JsonCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Producer {
    private static final Logger LOG = LogManager.getLogger(Producer.class);

    //submit into queue
    public static boolean submit(List<User> userList) {
        if (userList == null) {
            LOG.info("listQrTerminalPo is null");
            return false;
        }
        Channel channel = null;
        try {

            channel.queueDeclare(ChannelPool.queueName, true, false, false, null);
            for (User user : userList) {
                String messageStr = JsonCustom.convertObjectToJson(user);
                byte[] message = messageStr.getBytes("UTF-8");
                LOG.info("Begin submit data: " + messageStr);

                channel.basicPublish("", ChannelPool.queueName, null, message);
                LOG.info("End submit data");
            }
        } catch (Exception e) {
            LOG.error("Error submit: ", e);
            return false;
        }
        return true;
    }
}



