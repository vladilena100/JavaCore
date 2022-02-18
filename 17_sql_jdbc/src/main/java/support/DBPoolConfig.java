package com.solutions.vasylieva.support;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DBPoolConfig extends AbstractDBConfig {

    private static final Logger LOG = LogManager.getLogger(DBPoolConfig.class);

    private HikariDataSource dataSource;
    private HikariConfig config;

    public DBPoolConfig(String propertyFileName) {
        super(propertyFileName);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    protected void initDBConfig() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = DBPoolConfig.class.getClassLoader().getResourceAsStream(getPropertyFileName());
            if (inputStream == null) {
                LOG.error("File not found {}", getPropertyFileName());
                throw new RuntimeException(getPropertyFileName() + " not found");
            }
            properties.load(inputStream);
            config = new HikariConfig();
            String property = properties.getProperty("database.jdbc-url");
            config.setJdbcUrl(property);
            config.setUsername(properties.getProperty("database.username"));
            config.setPassword(properties.getProperty("database.password"));
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("database.max-pool-size")));
            config.setMinimumIdle(Integer.parseInt(properties.getProperty("database.min-idle")));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            LOG.error("Wrong database configuration ", e);
            throw new RuntimeException("Error set database configuration");
        }
    }
}
