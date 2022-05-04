package support;

import dao.Dao;
import dao.jdbc.JdbcRoleDao;
import model.Role;

public class RoleDAOFactory implements DAOFactory<Role> {
    @Override
    public Dao<Role> getDao() {
        return new JdbcRoleDao(ConnectionManager.getInstance(new DBPoolConfig(JDBC_PROPERTIES)));
    }
}
