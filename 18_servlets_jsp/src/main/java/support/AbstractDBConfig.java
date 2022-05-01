package support;

import javax.sql.DataSource;

public abstract class AbstractDBConfig {

    private final String propertyFileName;

    public AbstractDBConfig(String propertyFileName) {
        this.propertyFileName = propertyFileName;
        initDBConfig();
    }

    public abstract DataSource getDataSource();

    public String getPropertyFileName() {
        return propertyFileName;
    }

    abstract protected void initDBConfig();
}
