package dao;


import model.Role;

/**
 * DaoRole
 *
 * @author Vladilena Vasilieva
 */

public interface DaoRole extends Dao<Role> {

    @Override
    void create(Role entity);

    @Override
    void update(Role entity);

    @Override
    void remove(Role entity);

    Role findById(String name);
}
