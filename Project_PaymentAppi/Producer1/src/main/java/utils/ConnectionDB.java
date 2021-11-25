package utils;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.Property;
import lombok.extern.log4j.Log4j2;

import java.util.Properties;

@Log4j2
public class ConnectionDB {
    private static final String DB_USERNAME = "db.username";
    private static final String DB_URL = "db.url";
    private static HikariConfig jdbcConfig = new HikariConfig();
    private static HikariDataSource ds = null;
    private static Properties properties = Property.getInstance();

    static {
        try {
            log.info("Begin create HikariDataSource");

            jdbcConfig.setJdbcUrl(properties.getProperty(DB_URL));
            jdbcConfig.setUsername(properties.getProperty(DB_USERNAME));
            log.info(" Db url: " + jdbcConfig.getJdbcUrl() + " db user name: " + jdbcConfig.getUsername() + " db password: " + jdbcConfig.getPassword());
            ds = new HikariDataSource(jdbcConfig);
            log.info("End create HikariDataSource");
        } catch (Exception e) {
            log.error("Error create HikariDataSource: ", e);
        }
    }

    public static HikariDataSource getDataSource() {
        return ds;
    }

    private ConnectionDB() {
    }
}
