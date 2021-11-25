package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Log4j2
public class BasicChannelPool implements ChannelPool<Channel> {

    private final static String RABBITMQ_HOST = "localhost";
    private final static int RABBITMQ_PORT = 5672;
    private static BasicChannelPool intance;
    private static ConnectionFactory factory;
    private static Connection conn;

    private static final long EXPIRED_TIME_IN_MILISECOND = 10;
    private static final int NUMBER_OF_CHANNEL = 10;
    private AtomicInteger count = new AtomicInteger(0);
    private AtomicBoolean waiting = new AtomicBoolean(false);

    private final List<Channel> available = Collections.synchronizedList(new ArrayList<>());
    private final List<Channel> inUse = Collections.synchronizedList(new ArrayList<>());

    private BasicChannelPool() {
    }

    public static BasicChannelPool getInstance() {

        if (intance == null) {
            synchronized (BasicChannelPool.class) {
                if (null == intance) {
                    log.info("Begin create channel pool");
                    intance = new BasicChannelPool();
                    try {
                        factory = new ConnectionFactory();
                        factory.setHost(RABBITMQ_HOST);
                        factory.setPort(RABBITMQ_PORT);
                        log.info("Host : " + factory.getHost());
                        conn = factory.newConnection();
                        log.info("End create !!!!");
                    } catch (Exception e) {
                        log.error("Error create _________________________");
                    }
                }
            }

        }

        return intance;
    }

    @Override
    public synchronized Channel getChannel() {
        if (!available.isEmpty()) {
            Channel channel = available.remove(0);
            inUse.add(channel);
            return channel;
        }
        if (count.get() == NUMBER_OF_CHANNEL) {
            System.out.println("Waitting ..............");
            this.waitingChannelAvailable();
            return this.getChannel();
        }

        Channel channel = null;
        try {
            channel = this.createChannel(conn);
        } catch (IOException e) {
            e.printStackTrace();
        }
        inUse.add(channel);
        return channel;

    }

    @Override
    public void releaseChannel(Channel channel) {
        if (channel != null) {
            inUse.remove(channel);
            available.add(channel);
        }
    }

    @Override
    public int getSize() {
        System.out.println("channel using " + inUse.size());
        return inUse.size() + available.size();
    }

    private Channel createChannel(Connection conn) throws IOException {
        System.out.println("channel_number: " + count.getAndIncrement());
        return conn.createChannel();
    }

    private void waitingChannelAvailable() {
        if (waiting.get()) {
            waiting.set(false);
            log.info(" No channel available");
            throw new RuntimeException("No channel available ");

        }
        waiting.set(true);
        waiting(EXPIRED_TIME_IN_MILISECOND);


    }

    private void waiting(long numberOfSecond) {
        try {
            TimeUnit.MILLISECONDS.sleep(numberOfSecond);
        } catch (InterruptedException e) {
            log.error("Error create channel ", e);
            Thread.currentThread().interrupt();
        }
    }

}







