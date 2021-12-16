package com.example.producer.constant;

public class RabbitMQConst {
    public final static String QUEUE_DATA_PAYMENT = "queue.data_payment";
    public final static String DIRECT_EXCHANGE_PAYMENT = "direct.exchange_payment";
    public final static String HOST="localhost";
    public final static int MAX_TOTAL=10;
    public final static int MAX_IDLE = 10 ;
    public final static int MIN_IDLE = 2 ;
    public final static String RABBIT_EXCHANGE_DIRECT_TYPE="direct";
    public final static String KEY_PAYMENT = "payment";

}
