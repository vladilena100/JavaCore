package services;

import dao.DaoUser;
import dao.jdbc.JdbcUserDaoImpl;
import model.User;
import support.ConnectionManager;
import support.DBPoolConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService implements DaoUser {

    private static final Logger LOG = LogManager.getLogger(UserService.class);

    private DaoUser daoUser;

    public UserService(DaoUser daoUser) {
        this.daoUser = new JdbcUserDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")));
    }

    @Override
    public User findById(Long id) {
        return daoUser.findById(id);
    }

    @Override
    public void create(User user) {

        if (daoUser.findByEmail(user.getEmail()) == null || daoUser.findByLogin(user.getLogin()) == null) {

            daoUser.create(user);
        } else {
            LOG.info("user with this login or email already exists ");
        }
    }

    @Override
    public void update(User user) {
        if (daoUser.findByEmail(user.getEmail()) == null) {
            daoUser.update(user);
        }
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
}
