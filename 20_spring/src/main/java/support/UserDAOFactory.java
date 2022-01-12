package support;


import dao.Dao;
import dao.hibernate.HibernateUserDaoImpl;
import model.User;

public class UserDAOFactory implements DAOFactory<User> {

    @Override
    public Dao<User> getDao() {
        return new HibernateUserDaoImpl(HibernateSession.getInstance());
    }
}
