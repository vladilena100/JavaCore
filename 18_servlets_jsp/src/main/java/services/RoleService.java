package services;

import dao.DaoRole;
import dao.jdbc.JdbcRoleDaoImpl;
import dao.jdbc.JdbcUserDaoImpl;
import model.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.ConnectionManager;
import support.DBPoolConfig;

import java.util.List;

public class RoleService implements DaoRole {

    private static final Logger LOG = LogManager.getLogger(RoleService.class);

    private DaoRole roleDao;

    public RoleService(DaoRole roleDao) {
        this.roleDao = new JdbcRoleDaoImpl(ConnectionManager.getInstance(new DBPoolConfig("jdbc.properties")));
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
