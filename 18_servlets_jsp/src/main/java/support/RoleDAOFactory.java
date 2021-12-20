package support;

import dao.Dao;
import dao.jdbc.JdbcRoleDaoImpl;
import model.Role;

public class RoleDAOFactory implements DAOFactory<Role> {
    @Override
    public Dao<Role> getDao() {
        return new JdbcRoleDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance(JDBC_PROPERTIES)));
    }
}
