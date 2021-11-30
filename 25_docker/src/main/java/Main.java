import dao.DaoRole;
import dao.DaoUser;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.DAOFactory;
import support.RoleDAOFactory;
import support.UserDAOFactory;
import util.DBUtil;

import java.sql.Date;
import java.util.List;

/**
 * Main class
 *
 * @author Vladilena Vasilieva
 */

public class Main {

    private static final Logger LOG = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DBUtil.deleteTables();
        LOG.info("Create tables...");
        DBUtil.createTables();
        DAOFactory<User> factoryUser = new UserDAOFactory();
        DAOFactory<Role> factoryRole = new RoleDAOFactory();
        DaoRole roleDao;
        DaoUser userDao;
        userDao = (DaoUser) factoryUser.getDao();
        roleDao = (DaoRole) factoryRole.getDao();
        roleDao.create(new Role(1L, "USER"));
        roleDao.create(new Role(2L, "ADMIN"));
        userDao.create(new User(
                1L, "vlada", "123456",
                "vlada@gmail.com", "Vlada",
                "Vasylieva", Date.valueOf("1998-11-23"), null));
        userDao.create(new User(
                2L, "vasya", "654321",
                "vasya@gmail.com", "Vasya",
                "Pupkin", Date.valueOf("1995-10-02"), null));
        final List<User> allUsers = userDao.findAll();
        LOG.info("all users: {}", allUsers);
    }
}
