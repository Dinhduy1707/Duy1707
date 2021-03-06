package com.example.consumer.config.connectionDB;

import com.example.consumer.common.CommonDB;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
@Component
public class ConnectionDB {
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        config.setJdbcUrl(CommonDB.DATABASE_URL);
        config.setUsername(CommonDB.DATABASE_USERNAME);
        config.setMaximumPoolSize(CommonDB.MAX_POOL_SIZE);
        config.setDriverClassName(CommonDB.DATABASE_DRIVER_CLASS_NAME);
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        ds = new HikariDataSource(config);
    }

    private ConnectionDB() {
    }
    @Bean
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
