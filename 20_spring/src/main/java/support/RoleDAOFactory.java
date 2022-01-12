package support;

import dao.Dao;
import dao.hibernate.HibernateRoleDaoImpl;
import model.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOFactory implements DAOFactory<Role> {
    @Override
    public Dao<Role> getDao() {
        return new HibernateRoleDaoImpl(new DBConfig().sessionFactoryBean().getObject());
    }
}
