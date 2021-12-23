package services;

import dao.DaoUser;
import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.ConnectionManager;
import support.DBPoolConfig;

import java.util.List;

public class UserService implements DaoUser {

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private final DaoUser daoUser;

    public UserService(DaoUser daoUser) {
        this.daoUser = new JdbcUserDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties")));
    }

    @Override
    public User findById(Long id) {
        return daoUser.findById(id);
    }

    @Override
    public void create(User user) {
        daoUser.create(user);
    }

    @Override
    public void update(User user) {
        daoUser.update(user);
    }

    @Override
    public void remove(User user) {
        daoUser.remove(user);
    }

    @Override
    public List<User> findAll() {
        return daoUser.findAll();
    }

    @Override
    public User findByLogin(String login) {
        return daoUser.findByLogin(login);
    }

    @Override
    public User findByEmail(String email) {
        return daoUser.findByEmail(email);
    }

    public User getUserByLoginPassword(String login, String password) {

        User byLogin = daoUser.findByLogin(login);

        User result = null;

        if (byLogin != null && password.equals(byLogin.getPassword())) {
            return result = byLogin;
        } else {
            LOG.error("such user does not exist");
        }
        return result;
    }
}
