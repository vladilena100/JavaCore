//package dao.hibernate;
//
//import model.Role;
//import model.User;
//import org.dbunit.Assertion;
//import org.dbunit.DataSourceBasedDBTestCase;
//import org.dbunit.database.DatabaseConfig;
//import org.dbunit.database.DefaultMetadataHandler;
//import org.dbunit.database.IDatabaseConnection;
//import org.dbunit.dataset.IDataSet;
//import org.dbunit.dataset.ITable;
//import org.dbunit.dataset.filter.DefaultColumnFilter;
//import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
//import org.dbunit.ext.postgresql.PostgresqlDataTypeFactory;
//import org.dbunit.operation.DatabaseOperation;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//import support.DBConfig;
//
//import javax.sql.DataSource;
//import java.io.InputStream;
//import java.sql.Date;
//import java.util.List;
//
//@RunWith(JUnit4.class)
//public class HibernateUserDaoImplTest extends DataSourceBasedDBTestCase {
//
//    private static HibernateUserDaoImpl hibernateUserDao;
//    private static DBConfig hibernateSession;
//    private static DataSource dataSource;
//
//    @Override
//    protected DataSource getDataSource() {
//        return dataSource;
//    }
//
//    @BeforeClass
//    public static void init() {
//        hibernateSession = DBConfig.getInstance();
//        dataSource = hibernateSession.getDataSource();
//        hibernateUserDao = new HibernateUserDaoImpl(hibernateSession);
//    }
//
//    @Override
//    protected void setUpDatabaseConfig(DatabaseConfig config) {
//        config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "\"?\"");
//        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
//        config.setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new DefaultMetadataHandler());
//    }
//
//    @Override
//    protected IDataSet getDataSet() throws Exception {
//        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("user/user-data.xml"));
//    }
//
//    @Override
//    protected DatabaseOperation getSetUpOperation() {
//        return DatabaseOperation.CLEAN_INSERT;
//    }
//
//    @Override
//    protected DatabaseOperation getTearDownOperation() {
//        return DatabaseOperation.NONE;
//    }
//
//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        super.tearDown();
//    }
//
//    @Test
//    public void testFindById() throws Exception {
//        User byId = hibernateUserDao.findById(4L);
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream("user/user-by-id.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("user");
//            assertEquals(expectedTable.getValue(0, "id"), byId.getId().toString());
//            assertEquals(expectedTable.getValue(0, "login"), byId.getLogin());
//            assertEquals(expectedTable.getValue(0, "password"), byId.getPassword());
//            assertEquals(expectedTable.getValue(0, "email"), byId.getEmail());
//        }
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        hibernateUserDao.create(new User(5L, "max", "55555", "max@gmail.com", "Max", "Connor",
//                Date.valueOf("1985-08-24"), new Role(2L, "ADMIN")));
//        try (InputStream is = getClass().getResourceAsStream("/user/create-user.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("user");
//            ITable iTable1 = DefaultColumnFilter.includedColumnsTable(expectedTable, expectedTable.getTableMetaData().getColumns());
//
//            IDatabaseConnection connection = getConnection();
//            ITable actualTable = connection.createDataSet().getTable("user");
//            connection.close();
//            ITable iTable2 = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
//            Assertion.assertEquals(iTable1, iTable2);
//        }
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        hibernateUserDao.update(new User(3L, "max", "55555", "max@gmail.com", "Max", "Connor",
//                Date.valueOf("1985-08-24"), new Role(2L, "ADMIN")));
//        try (InputStream is = getClass().getResourceAsStream("/user/update-user.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("user");
//
//            IDatabaseConnection connection = getConnection();
//            ITable actualTable = connection.createDataSet().getTable("user");
//            connection.close();
//            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
//            Assertion.assertEquals(expectedTable, iTable);
//        }
//    }
//
//    @Test
//    public void testRemove() throws Exception {
//        hibernateUserDao.remove(new User(4L, "john", "44444", "john@gmail.com", "John", "Catusso",
//                Date.valueOf("1994-02-09"), new Role(2L, "ADMIN")));
//        try (InputStream is = getClass().getResourceAsStream("/user/remove-user.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("user");
//
//            IDatabaseConnection connection = getConnection();
//            ITable actualTable = connection.createDataSet().getTable("user");
//            connection.close();
//            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
//            Assertion.assertEquals(expectedTable, iTable);
//        }
//    }
//
//    @Test
//    public void testFindAll() throws Exception {
//        List<User> all = hibernateUserDao.findAll();
//        IDataSet expectedDataSet = getDataSet();
//        ITable expectedTable = expectedDataSet.getTable("user");
//
//        assertEquals(expectedTable.getRowCount(), all.size());
//    }
//
//    @Test
//    public void testFindByLogin() throws Exception {
//        User vlada = hibernateUserDao.findByLogin("john");
//        IDataSet expectedDataSet = getDataSet();
//        ITable expectedTable = expectedDataSet.getTable("user");
//
//        assertEquals(expectedTable.getValue(3, "id"), vlada.getId().toString());
//    }
//
//}