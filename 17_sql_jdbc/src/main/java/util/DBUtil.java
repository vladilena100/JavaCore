package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.ConnectionManager;
import support.DBConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DBUtil
 *
 * @author Vladilena Vasilieva
 */

public class DBUtil {

    private static final Logger LOG = LogManager.getLogger(DBUtil.class);

    private static ConnectionManager connectionManager = ConnectionManager.getInstance(new DBConfig("jdbc.properties"));

    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS \"user\"" +
            "(" +
            "    id SERIAL PRIMARY KEY," +
            "    login VARCHAR(64) NOT NULL," +
            "    password VARCHAR(255) NOT NULL UNIQUE," +
            "    email VARCHAR(64) NOT NULL UNIQUE," +
            "    first_name VARCHAR(64) NOT NULL," +
            "    last_name VARCHAR(64) NOT NULL," +
            "    birthday TIMESTAMP NOT NULL," +
            "    role_id BIGINT NOT NULL," +
            "    FOREIGN KEY (role_id) REFERENCES role(role_id)" +
            ");";

    public static final String CREATE_ROLE_TABLE = "CREATE TABLE IF NOT EXISTS role" +
            "(" +
            "role_id SERIAL PRIMARY KEY," +
            "role_name VARCHAR(64) NOT NULL" +
            ");";

    public static final String DROP_USER_TABLE = "DROP TABLE IF EXISTS \"user\" CASCADE";
    public static final String DROP_ROLE_TABLE = "DROP TABLE IF EXISTS role CASCADE";

    private DBUtil() {

    }

    public static void createTables() {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(CREATE_ROLE_TABLE);
            LOG.info("Table user create successful");

            statement.executeUpdate(CREATE_USER_TABLE);
            LOG.info("Table user create successful");

        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

    }

    public static void deleteTables() {
        try (Connection connection = connectionManager.getConnection();
             Statement statement = connection.createStatement()) {

            statement.executeUpdate(DROP_USER_TABLE);
            LOG.info("DB user delete successful");

            statement.executeUpdate(DROP_ROLE_TABLE);
            LOG.info("DB role delete successful");
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
