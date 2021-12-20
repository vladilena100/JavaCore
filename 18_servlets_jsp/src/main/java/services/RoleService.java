package services;

import dao.DaoRole;
import dao.jdbc.JdbcRoleDaoImpl;
import model.Role;
import support.ConnectionManager;
import support.DBPoolConfig;

import java.util.List;

public class RoleService implements DaoRole {

    private final DaoRole roleDao;

    public RoleService(DaoRole roleDao) {
        this.roleDao = new JdbcRoleDaoImpl(ConnectionManager.getInstance(DBPoolConfig.getInstance("jdbc.properties")));
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public void create(Role role) {
        roleDao.create(role);
    }

    @Override
    public void update(Role role) {
        roleDao.update(role);
    }

    @Override
    public void remove(Role role) {
        roleDao.remove(role);
    }

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

}
