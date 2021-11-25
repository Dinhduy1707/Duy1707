package start;

import common.CommonPool;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import service.ReceiveMessages;
import utils.BasicChannelPool;
import utils.ConnectionDB;

import java.util.concurrent.Executors;

@Log4j2
public class Consumer {

    public static void main(String[] argv) {
        CommonPool.threadPool = Executors.newCachedThreadPool();
        CommonPool.basicChannelPool = BasicChannelPool.getInstance();
        CommonPool.hikariDataSource =  ConnectionDB.getDataSource();
        log.info("Begin read message from queue");
        CommonPool.threadPool.submit(new ReceiveMessages());
        log.info("End read message from queue");
        CommonPool.threadPool.shutdown();
        System.out.println("hello");
        ThreadContext.clearAll();
    }
}
