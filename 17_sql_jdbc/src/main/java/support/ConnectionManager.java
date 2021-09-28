package support;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ConnectionManager
 *
 * @author Vladilena Vasilieva
 */

public class ConnectionManager {

    private static final Logger LOG = LogManager.getLogger(ConnectionManager.class);

    private HikariDataSource dataSource;
    private HikariConfig config;
    private static ConnectionManager connectionManager;
    private final String propertyFile;


    public ConnectionManager(String propertyFile) {
        this.propertyFile = propertyFile;
        initDBconfig();
    }

    public static ConnectionManager getInstance(String propertyFile) {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager(propertyFile);
        }
        return connectionManager;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public DataSource getDataSourse() {
        return dataSource;
    }

    /**
     * method init HicariCP
     */

    public void initDBconfig() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = ConnectionManager.class.getClassLoader().getResourceAsStream(this.propertyFile);
            if (inputStream == null) {
                throw new IOException(this.propertyFile + "not found");
            }
            properties.load(inputStream);
            if (config == null) {
                config = new HikariConfig();
            }
            String property = properties.getProperty("database.jdbc-url");
            config.setJdbcUrl(property);
            config.setDriverClassName("org.postgresql.Driver");
            config.setUsername(properties.getProperty("database.username"));
            config.setPassword(properties.getProperty("database.password"));
            config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("database.max-pool-size")));
            config.setMinimumIdle(Integer.parseInt(properties.getProperty("database.min-idle")));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

            if (dataSource == null) {
                dataSource = new HikariDataSource(config);
                dataSource.setIdleTimeout(100);
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
