package support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

public class DBConfig extends AbstractDBConfig{

    private static final Logger LOG = LogManager.getLogger(DBConfig.class);
    private String url;
    private String userName;
    private String password;

    public DBConfig(String propertyFileName) {
        super(propertyFileName);
    }

    @Override
    public DataSource getDataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(url);
        dataSource.setUser(userName);
        dataSource.setPassword(password);
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
            url = properties.getProperty("database.jdbc-url");
            userName = properties.getProperty("database.username");
            password = properties.getProperty("database.password");
        } catch (Exception e) {
            LOG.error("Wrong database configuration ", e);
            throw new RuntimeException("Error set database configuration");
        }
    }
}
