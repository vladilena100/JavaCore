package dao.hibernate;

import dao.DaoUser;
import exception.HibernateDaoException;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import support.HibernateSession;

import java.util.List;

/**
 * JdbcUserDao
 *
 * @author Vladilena Vasilieva
 */

public class HibernateUserDaoImpl implements DaoUser {

    private static final Logger LOG = LogManager.getLogger(HibernateUserDaoImpl.class);

    private final HibernateSession hibernateSession;

    public HibernateUserDaoImpl(HibernateSession hibernateSession) {
        this.hibernateSession = hibernateSession;
    }

    /**
     * Метод находит пользователей по id
     *
     * @param id id для поиска пользователя
     * @return пользователь из базы
     */

    @Override
    public User findById(Long id) {

        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {
            transaction = session.beginTransaction();

            Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE u.id = :id");
            query.setParameter("id", id);
            User user = query.uniqueResult();

            transaction.commit();
            return user;
        } catch (Exception e) {
            LOG.error("Error when finding user by id", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод создает пользователя в базе
     *
     * @param user пользователь которого нужно добавить в базу
     */

    @Override
    public void create(User user) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            session.save(user);

            transaction.commit();
        } catch (Exception e) {
            LOG.error("Error when creating new user", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод апдейтит пользователя по id в базе
     *
     * @param user пользователь для апдейта в базе
     */

    @Override
    public void update(User user) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            session.update(user);

            transaction.commit();
        } catch (Exception e) {
            LOG.error("Error when updating user", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод удаляет пользователя из базы
     *
     * @param user пользователь для удаления из базы
     */

    @Override
    public void remove(User user) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM User WHERE id = :id");
            query.setParameter("id", user.getId());

            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            LOG.error("Error when removing user", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод находит всех пользователей в базе
     *
     * @return список пользователей
     */

    @Override
    public List<User> findAll() {

        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();

            Query<User> userQuery = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r");
            List<User> users = userQuery.getResultList();
            transaction.commit();

            return users;
        } catch (Exception e) {
            LOG.error("Error when finding all users", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод находит пользователя по логину в базе
     *
     * @param login логин по которому производится поиск
     * @return пользователь из базы
     */

    @Override
    public User findByLogin(String login) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE u.login = :login");
            query.setParameter("login", login);
            User user = query.uniqueResult();

            transaction.commit();
            return user;
        } catch (Exception e) {
            LOG.error("Error when finding user by login", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод находит пользователя по email в базе
     *
     * @param email email по которому производится поиск
     * @return пользователь из базы
     */

    @Override
    public User findByEmail(String email) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE u.email = :email");
            query.setParameter("email", email);
            User user = query.uniqueResult();

            transaction.commit();
            return user;
        } catch (Exception e) {
            LOG.error("Error when finding user by email", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }
}
