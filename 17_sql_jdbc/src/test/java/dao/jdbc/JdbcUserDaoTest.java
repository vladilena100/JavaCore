package dao.jdbc;

import model.Role;
import model.User;
import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import support.ConnectionManager;
import support.DBPoolConfig;

import javax.sql.DataSource;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

/**
 * TestJdbcUserDao
 *
 * @author Vladilena Vasilieva
 */

@RunWith(JUnit4.class)
public class JdbcUserDaoTest extends DataSourceBasedDBTestCase {

    public static final String JDBC_TEST_PROPERTIES = "jdbc-test.properties";
    private static JdbcUserDao jdbcUserDao;
    private static ConnectionManager connectionManager;
    private static DataSource dataSource;

    @Override
    protected DataSource getDataSource() {
        return dataSource;
    }

    @BeforeClass
    public static void init() {
        connectionManager = ConnectionManager.getInstance(new DBPoolConfig(JDBC_TEST_PROPERTIES));
        dataSource = connectionManager.getDataSource();
        jdbcUserDao = new JdbcUserDao(connectionManager);
    }

    @Override
    protected void setUpDatabaseConfig(DatabaseConfig config) {
        config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "\"?\"");
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("user/user-data.xml"));
    }

    @Override
    protected DatabaseOperation getSetUpOperation() {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() {
        return DatabaseOperation.DELETE_ALL;
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testFindById() throws Exception {
        User byId = jdbcUserDao.findById(3L);
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("user/user-by-id.xml")){
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedTable = expectedDataSet.getTable("user");
            assertEquals(expectedTable.getValue(0, "id"), byId.getId().toString());
            assertEquals(expectedTable.getValue(1, "id"), byId.getId().toString());
            assertEquals(expectedTable.getValue(2, "id"), byId.getId().toString());
            assertEquals(expectedTable.getValue(3, "id"), byId.getId().toString());
        }
    }

    @Test
    public void testCreate() throws Exception {
        jdbcUserDao.create(new User(5L, "max", "55555", "max@gmail.com", "Max", "Connor",
                Date.valueOf("1985-08-24"), new Role(2L, "ADMIN")));
        try (InputStream is = getClass().getResourceAsStream("/user/create-user.xml")){
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedTable = expectedDataSet.getTable("user");

            IDatabaseConnection connection = getConnection();
            ITable actualTable = connection.createDataSet().getTable("user");
            connection.close();
            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
            Assertion.assertEquals(expectedTable, iTable);
        }
    }

    @Test
    public void testUpdate() throws Exception {
        jdbcUserDao.update(new User(3L, "max", "55555", "max@gmail.com", "Max", "Connor",
                Date.valueOf("1985-08-24"), new Role(2L, "ADMIN")));
        try (InputStream is = getClass().getResourceAsStream("/user/update-user.xml")){
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedTable = expectedDataSet.getTable("user");

            IDatabaseConnection connection = getConnection();
            ITable actualTable = connection.createDataSet().getTable("user");
            connection.close();
            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
            Assertion.assertEquals(expectedTable, iTable);
        }
    }

    @Test
    public void testRemove() throws Exception {
        jdbcUserDao.remove(new User(4L, "john", "44444", "john@gmail.com", "John", "Catusso",
                Date.valueOf("1994-02-09"), new Role(2L, "ADMIN")));
        try (InputStream is = getClass().getResourceAsStream("/user/remove-user.xml")) {
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
            ITable expectedTable = expectedDataSet.getTable("user");

            IDatabaseConnection connection = getConnection();
            ITable actualTable = connection.createDataSet().getTable("user");
            connection.close();
            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
            Assertion.assertEquals(expectedTable, iTable);
        }
    }

    @Test
    public void testFindAll() throws Exception {
        List<User> all = jdbcUserDao.findAll();
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");

        assertEquals(expectedTable.getRowCount(), all.size());
    }

    @Test
    public void testFindByLogin() throws Exception {
        User vlada = jdbcUserDao.findByLogin("john");
        IDataSet expectedDataSet = getDataSet();
        ITable expectedTable = expectedDataSet.getTable("user");

        assertEquals(expectedTable.getValue(3, "id"), vlada.getId().toString());
    }
}