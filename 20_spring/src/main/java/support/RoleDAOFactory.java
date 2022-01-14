package support;

import dao.Dao;
import dao.DaoRole;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOFactory implements DAOFactory<Role> {

    @Autowired
    public DaoRole daoRole;

    @Override
    public Dao<Role> getDao() {
        return daoRole;
    }
}
