package com.solutions.vasylieva.support;

import com.solutions.vasylieva.dao.Dao;
import com.solutions.vasylieva.dao.jdbc.JdbcRoleDao;
import model.Role;

public class RoleDAOFactory implements DAOFactory<Role> {
    @Override
    public Dao<Role> getDao() {
        return new JdbcRoleDao(ConnectionManager.getInstance(new DBPoolConfig(JDBC_PROPERTIES)));
    }
}
