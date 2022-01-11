package support;

import dao.Dao;
import dao.hibernate.HibernateRoleDaoImpl;
import model.Role;

public class RoleDAOFactory implements DAOFactory<Role> {
    @Override
    public Dao<Role> getDao() {
        return new HibernateRoleDaoImpl(DBConfig.getInstance());
    }
}
