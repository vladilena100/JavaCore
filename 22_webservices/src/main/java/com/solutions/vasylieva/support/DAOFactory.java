package com.solutions.vasylieva.support;

import com.solutions.vasylieva.dao.Dao;

public interface DAOFactory<E> {
    Dao<E> getDao();
}
