package com.solutions.vasylieva.support;


import com.solutions.vasylieva.dao.Dao;
import com.solutions.vasylieva.dao.DaoUser;
import com.solutions.vasylieva.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOFactory implements DAOFactory<User> {

    @Autowired
    public DaoUser daoUser;

    @Override
    public Dao<User> getDao() {
        return daoUser;
    }
}
