package support;

import dao.Dao;

public interface DAOFactory<E> {
    Dao<E> getDao();
}
