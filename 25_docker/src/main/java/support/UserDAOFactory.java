package support;

import dao.DaoUser;
import dao.jdbc.JdbcUserDao;
import model.User;


/**
 * DaoFactory
 *
 * @author Vladilena Vasilieva
 */

public class UserDAOFactory implements DAOFactory<User> {

    public DaoUser getDao() {
        return new JdbcUserDao(ConnectionManager.getInstance(new DBPoolConfig(JDBC_PROPERTIES)));
    }
}
