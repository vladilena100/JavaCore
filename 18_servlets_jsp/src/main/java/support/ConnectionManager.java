package support;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ConnectionManager
 *
 * @author Vladilena Vasilieva
 */

public final class ConnectionManager {

    private static volatile ConnectionManager connectionManager;
    private final AbstractDBConfig dbConfig;

    private ConnectionManager(AbstractDBConfig dbConfig) {
        this.dbConfig = dbConfig;
    }

    public static ConnectionManager getInstance(AbstractDBConfig dbConfig) {

        if (connectionManager == null) {
            synchronized (ConnectionManager.class) {
                if (connectionManager == null) {
                    connectionManager = new ConnectionManager(dbConfig);
                }
            }
        }
        return connectionManager;
    }

    public Connection getConnection() throws SQLException {
        return dbConfig.getDataSource().getConnection();
    }

    public Connection getTransactionalConnection() throws SQLException {
        Connection connection = dbConfig.getDataSource().getConnection();
        connection.setAutoCommit(false);
        return connection;
    }


    public DataSource getDataSource() {
        return dbConfig.getDataSource();
    }

}
