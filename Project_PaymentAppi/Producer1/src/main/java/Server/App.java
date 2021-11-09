package Server;

import common.CommonPool;
import common.LogCommon;
import common.Property;
import controller.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import utils.BasicChannelPool;
import utils.ConnectionDB;

import java.util.Properties;


public class App {

   private static final Logger LOG = LogManager.getLogger(App.class);
    private static final String SERVER_PORT = "server.port";
    private static final Properties properties = Property.getInstance();

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        Server jettyServer = new Server(Integer.parseInt(properties.getProperty(SERVER_PORT)));
        jettyServer.setHandler(context);
        ServletHolder jerseyServlet = context.addServlet(
                ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
        jerseyServlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                UserController.class.getCanonicalName());

       // create pool
       CommonPool.hikariDataSource = ConnectionDB.getDataSource();
       CommonPool.basicChannelPool = BasicChannelPool.getInstance();
      // CommonPool.sentinelPool = ConnectRedis.getJedisSentinelPool();

        // start server
        try {
            jettyServer.start();
            LOG.info("Server started with port: "+properties.getProperty(SERVER_PORT));
            System.out.println("hello");
            ThreadContext.clearAll();
            jettyServer.join();
        } catch (Exception e) {
            System.out.println("Error Start:");
            System.out.println(e.getMessage());
            LOG.error("Error start server: ",e);
        } finally {
            jettyServer.destroy();
        }

    }
}
