package support;

import java.lang.reflect.InvocationTargetException;

/**
 * DaoFactory
 *
 * @author Vladilena Vasilieva
 */

public class DAOFactory {

    public <T>T getDao(Class<T> clazz, String properties) throws NoSuchMethodException,
                                                                 IllegalAccessException,
                                                                 InstantiationException,
                                                                 InvocationTargetException {
        return clazz.getConstructor(ConnectionManager.class)
                .newInstance(ConnectionManager
                        .getInstance(properties));
    }
}
