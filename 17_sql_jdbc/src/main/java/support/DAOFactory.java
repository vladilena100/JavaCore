package com.solutions.vasylieva.support;

import com.solutions.vasylieva.dao.Dao;

public interface DAOFactory<E> {

    String JDBC_PROPERTIES = "jdbc.properties";

    public Dao<E> getDao();
}
