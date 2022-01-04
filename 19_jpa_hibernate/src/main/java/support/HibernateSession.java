package support;

import model.Role;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sql.DataSource;

public class HibernateSession {

    private static HibernateSession hibernateSession;
    private SessionFactory sessionFactory;

    private HibernateSession () {
        initDBConfig();
    }

    public static synchronized HibernateSession getInstance() {
        if (hibernateSession == null) {
            hibernateSession = new HibernateSession();
        }
        return hibernateSession;
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    private void initDBConfig() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Role.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public DataSource getDataSource() {
        DataSource dataSource;
        return getDataSource();
    }
}
