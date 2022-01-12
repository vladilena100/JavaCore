//package dao.hibernate;
//
//import model.Role;
//import org.dbunit.Assertion;
//import org.dbunit.DataSourceBasedDBTestCase;
//import org.dbunit.database.DatabaseConfig;
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
//import support.HibernateSession;
//
//import javax.sql.DataSource;
//import java.io.InputStream;
//import java.util.List;
//
//@RunWith(JUnit4.class)
//public class HibernateRoleDaoImplTest extends DataSourceBasedDBTestCase {
//
//    private static HibernateRoleDaoImpl hibernateRoleDao;
//    private static HibernateSession hibernateSession;
//    private static DataSource dataSource;
//
//    @Override
//    protected DataSource getDataSource() {
//        return dataSource;
//    }
//
//    @BeforeClass
//    public static void init() {
//        hibernateSession = HibernateSession.getInstance();
//        dataSource = hibernateSession.getDataSource();
//        hibernateRoleDao = new HibernateRoleDaoImpl(hibernateSession);
//    }
//
//    @Override
//    protected void setUpDatabaseConfig(DatabaseConfig config) {
//        config.setProperty(DatabaseConfig.PROPERTY_ESCAPE_PATTERN, "\"?\"");
//        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new PostgresqlDataTypeFactory());
//    }
//
//    @Override
//    protected IDataSet getDataSet() throws Exception {
//        return new FlatXmlDataSetBuilder().build(getClass().getClassLoader().getResourceAsStream("role/actual_role.xml"));
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
//    public void testFindAll() throws Exception {
//        List<Role> all = hibernateRoleDao.findAll();
//        IDataSet expectedDataSet = getDataSet();
//        ITable expectedTable = expectedDataSet.getTable("role");
//
//        assertEquals(expectedTable.getRowCount(), all.size());
//    }
//
//    @Test
//    public void testFindById() throws Exception {
//        Role byId = hibernateRoleDao.findById(1L);
//        try (InputStream is = getClass().getClassLoader().getResourceAsStream("role/role-by-id.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("role");
//            assertEquals(expectedTable.getValue(0, "role_id"), byId.getId().toString());
//        }
//    }
//
//    @Test
//    public void testCreate() throws Exception {
//        hibernateRoleDao.create(new Role("SUPER_ADMIN"));
//        try (InputStream is = getClass().getResourceAsStream("/role/expected-role.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("role");
//            ITable iTable1 = DefaultColumnFilter.includedColumnsTable(expectedTable, expectedTable.getTableMetaData().getColumns());
//
//
//            IDatabaseConnection connection = getConnection();
//            ITable actualTable = connection.createDataSet().getTable("role");
//            connection.close();
//            ITable iTable2 = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
//            Assertion.assertEquals(iTable1, iTable2);
//        }
//    }
//
//    @Test
//    public void testUpdate() throws Exception {
//        hibernateRoleDao.update(new Role(2L, "SUPER_ADMIN"));
//        try (InputStream is = getClass().getResourceAsStream("/role/update-role.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("role");
//
//            IDatabaseConnection connection = getConnection();
//            ITable actualTable = connection.createDataSet().getTable("role");
//            connection.close();
//            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
//            Assertion.assertEquals(expectedTable, iTable);
//        }
//    }
//
//    @Test
//    public void testRemove() throws Exception {
//        hibernateRoleDao.remove(new Role(1L, "USER"));
//        try (InputStream is = getClass().getResourceAsStream("/role/remove-role.xml")) {
//            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(is);
//            ITable expectedTable = expectedDataSet.getTable("role");
//
//            IDatabaseConnection connection = getConnection();
//            ITable actualTable = connection.createDataSet().getTable("role");
//            connection.close();
//            ITable iTable = DefaultColumnFilter.includedColumnsTable(actualTable, expectedTable.getTableMetaData().getColumns());
//            Assertion.assertEquals(expectedTable, iTable);
//        }
//    }
//}