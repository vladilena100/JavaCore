package services;

import dao.Dao;
import dao.DaoRole;
import model.Role;

import java.util.List;

public class RoleService {

    private static volatile RoleService roleService;

    private final DaoRole roleDao;

    private RoleService(Dao<Role> roleDao) {
        this.roleDao = (DaoRole)roleDao;
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Role findByName(String name) {
        return roleDao.findByName(name);
    }

    public static RoleService getInstance(DaoRole roleDao) {
        if (roleService == null) {
            synchronized (RoleService.class) {
                if (roleService == null) {
                    roleService = new RoleService(roleDao);
                }
            }
        }
        return roleService;
    }

}
