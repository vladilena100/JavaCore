package support;

import dao.DaoUser;
import dao.jdbc.JdbcUserDaoImpl;
import model.User;


/**
 * DaoFactory
 *
 * @author Vladilena Vasilieva
 */

public class UserDAOFactory implements DAOFactory<User> {

    public DaoUser getDao() {
        return new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance(JDBC_PROPERTIES)));
    }
}
