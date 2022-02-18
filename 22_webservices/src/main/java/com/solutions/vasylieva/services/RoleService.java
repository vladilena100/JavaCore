package com.solutions.vasylieva.services;

import com.solutions.vasylieva.dao.DaoRole;
import com.solutions.vasylieva.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private DaoRole roleDao;

    public List<Role> findAll() {
        return roleDao.findAll();
    }

}
