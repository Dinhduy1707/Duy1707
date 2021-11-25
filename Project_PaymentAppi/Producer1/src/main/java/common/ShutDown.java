package common;

public class ShutDown {

    public static void shutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                if (CommonPool.hikariDataSource != null) {
                    CommonPool.hikariDataSource.close();
                }
                if (CommonPool.basicChannelPool != null) {
                    CommonPool.basicChannelPool = null;
                }
                if (CommonPool.threadPool != null) {
                    CommonPool.threadPool.shutdown();
                }

            }
        });
    }
}