package support;


import dao.Dao;
import dao.DaoUser;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOFactory implements DAOFactory<User> {

    @Autowired
    public DaoUser daoUser;

    @Override
    public Dao<User> getDao() {
        return daoUser;
    }
}
