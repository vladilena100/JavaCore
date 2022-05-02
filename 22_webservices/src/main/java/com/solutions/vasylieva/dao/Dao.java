package com.solutions.vasylieva.dao;

import java.util.List;

/**
 * Dao
 *
 * @author Vladilena Vasilieva
 */

public interface Dao<E> {

    void create(E entity);

    void update(E entity);

    void remove(E entity);

    List<E> findAll();

    E findById(Long id);
}
