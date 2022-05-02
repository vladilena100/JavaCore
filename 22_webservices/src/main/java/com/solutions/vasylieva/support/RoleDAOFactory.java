package com.solutions.vasylieva.support;


import com.solutions.vasylieva.dao.Dao;
import com.solutions.vasylieva.dao.DaoRole;
import com.solutions.vasylieva.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDAOFactory implements DAOFactory<Role> {

    @Autowired
    public DaoRole daoRole;

    @Override
    public Dao<Role> getDao() {
        return daoRole;
    }
}
