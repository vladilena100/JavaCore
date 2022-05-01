package support;

import dao.Dao;
import dao.jdbc.JdbcRoleDaoImpl;
import model.Role;

/**
 * DaoFactory
 *
 * @author Vladilena Vasilieva
 */

public class RoleDAOFactory implements DAOFactory<Role> {
    @Override
    public Dao<Role> getDao() {
        return new JdbcRoleDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance(JDBC_PROPERTIES)));
    }
}
