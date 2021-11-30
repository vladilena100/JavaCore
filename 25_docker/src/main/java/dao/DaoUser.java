package dao;

import model.User;

import javax.management.relation.Role;
import java.util.List;

/**
 * DaoUser
 *
 * @author Vladilena Vasilieva
 */

public interface DaoUser extends Dao<User> {

    @Override
    void create(User entity);

    @Override
    void update(User entity);

    @Override
    void remove(User entity);

    @Override
    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);
}
