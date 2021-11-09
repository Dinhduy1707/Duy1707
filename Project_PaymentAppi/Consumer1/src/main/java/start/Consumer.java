package start;

import common.CommonPool;
import common.LogCommon;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import service.ReceiveMessages;
import utils.BasicChannelPool;
import utils.ConnectionDB;


public class Consumer {
    private static final Logger LOG = LogManager.getLogger(Consumer.class);

    public static void main(String[] argv) {
        CommonPool.basicChannelPool = BasicChannelPool.getInstance();
        CommonPool.hikariDataSource = ConnectionDB.getDataSource();
        LOG.info("Begin read message from queue");
        CommonPool.threadPool.submit(new ReceiveMessages());
        LOG.info("End read message from queue");
        CommonPool.threadPool.shutdown();
        ThreadContext.clearAll();
    }
}
