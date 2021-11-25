package server;

import common.CommonPool;
import common.Property;
import common.ShutDown;
import controller.PaymentController;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.ThreadContext;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import utils.BasicChannelPool;
import utils.ConnectionDB;
import utils.RedisConnection;

import java.util.Properties;

@Log4j2
public class App {

    private static final String SERVER_PORT = "server.port";
    private static final Properties properties = Property.getInstance();

    public static void main(String[] args) {
        ShutDown.shutdownHook();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(Integer.parseInt(properties.getProperty(SERVER_PORT)));
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(
                ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                PaymentController.class.getCanonicalName());

        // create pool
        CommonPool.hikariDataSource = ConnectionDB.getDataSource();
        CommonPool.jedisPool = RedisConnection.getJedisPool();
        CommonPool.basicChannelPool = BasicChannelPool.getInstance();

        // start server
        try {
            jettyServer.start();
            log.info("Server started with port: " + properties.getProperty(SERVER_PORT));
            System.out.println("hello");
            ThreadContext.clearAll();
            jettyServer.join();
        } catch (Exception e) {
            System.out.println("Error Start:");
            System.out.println(e.getMessage());
            log.error("Error start server: ", e);
        } finally {
            jettyServer.destroy();
        }

    }
}
