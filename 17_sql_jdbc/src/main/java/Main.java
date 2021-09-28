import dao.jdbc.JdbcRoleDao;
import dao.jdbc.JdbcUserDao;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.DAOFactory;
import util.DBUtil;

import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.util.List;

/**
 * Main class
 *
 * @author Vladilena Vasilieva
 */

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);
    private static final String PROPERTY_PATH = "jdbc.property";

    public static void main(String[] args) {
        DBUtil.deleteTables();
        LOG.info("Create tables...");
        DBUtil.createTables();
        DAOFactory factory = new DAOFactory();
        JdbcRoleDao roleDao;
        JdbcUserDao userDao;
        try {
            userDao = factory.getDao(JdbcUserDao.class, PROPERTY_PATH);
            roleDao = factory.getDao(JdbcRoleDao.class, PROPERTY_PATH);
            roleDao.create(new Role(1L, "USER"));
            roleDao.create(new Role(2L, "ADMIN"));
            roleDao.create(new Role(3L, "SUPER_ADMIN"));
            Role roleUser = roleDao.findById(1L);
            final List<Role> all = roleDao.findAll();
            LOG.info("Roles: {}", all);
            LOG.info("Roles by ID: {}", roleUser);
            userDao.create(new User(
                    1L, "vlada", "123456",
                    "vlada@gmail.com", "Vlada",
                    "Vasylieva", Date.valueOf("1998-11-23"), null));
            userDao.create(new User(
                    2L, "vasya", "654321",
                    "vasya@gmail.com", "Vasya",
                    "Pupkin", Date.valueOf("1995-10-02"), null));
            User vlada = userDao.findByLogin("vlada");
            LOG.info("User by login: {}", vlada);
            userDao.remove(vlada);
            LOG.info("User successfuly removed");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            LOG.error("Can`t create ibject ", e);
        }
    }
}
