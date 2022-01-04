package support;

import dao.Dao;

public interface DAOFactory<E> {
    public Dao<E> getDao();
}
