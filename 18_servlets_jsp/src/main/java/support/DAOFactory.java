package support;

import dao.Dao;

public interface DAOFactory<E> {

    String JDBC_PROPERTIES = "jdbc.properties";

    Dao<E> getDao();
}
