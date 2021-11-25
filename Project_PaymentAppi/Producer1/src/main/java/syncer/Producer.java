package syncer;

import lombok.extern.log4j.Log4j2;
import model.Payment;
import com.rabbitmq.client.Channel;
import common.ChannelCommon;
import common.CommonPool;
import common.JsonCustom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@Log4j2
public class Producer {


    //submit into queue
    public static boolean submit(List<Payment> userList) {
        if (userList == null) {
            log.info("listUser is null");
            return false;
        }
        Channel channel = null;
        try {
            channel = CommonPool.basicChannelPool.getChannel();
            if (channel == null || !channel.isOpen()) {
                log.info("Not channel is available ");
                return false;
            }

            channel.queueDeclare(ChannelCommon.queueName, true, false, false, null);
            for (Payment user : userList) {
                String messageStr = JsonCustom.convertObjectToJson(user);
                byte[] message = messageStr.getBytes("UTF-8");
                log.info("Begin submit data: " + messageStr);

                channel.basicPublish("", ChannelCommon.queueName, null, message);
                log.info("End submit data");
            }
        } catch (Exception e) {
            log.error("Error submit: ", e);
            return false;
        } finally {
            CommonPool.basicChannelPool.releaseChannel(channel);
            log.debug("End channel");
        }
        return true;
    }
}



