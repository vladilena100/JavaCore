package services;


import dao.Dao;
import dao.DaoRole;
import model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private final DaoRole roleDao;

    private RoleService(Dao<Role> roleDao) {
        this.roleDao = (DaoRole) roleDao;
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }

    public Role findById(Long id) {
        return roleDao.findById(id);
    }
}
