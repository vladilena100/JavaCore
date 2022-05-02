package support;

import dao.Dao;
import dao.jdbc.JdbcUserDaoImpl;
import model.User;

/**
 * DaoFactory
 *
 * @author Vladilena Vasilieva
 */

public class UserDAOFactory implements DAOFactory<User> {

    @Override
    public Dao<User> getDao() {
        return new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance(JDBC_PROPERTIES)));
    }
}
