package dao.hibernate;

import dao.DaoRole;
import exception.HibernateDaoException;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import support.DBConfig;

import java.util.List;

/**
 * JdbcRoleDao
 *
 * @author Vladilena Vasilieva
 */

public class HibernateRoleDaoImpl implements DaoRole {

    private static final Logger LOG = LogManager.getLogger(HibernateRoleDaoImpl.class);

    private final DBConfig hibernateSession;

    public HibernateRoleDaoImpl(DBConfig hibernateSession) {
        this.hibernateSession = hibernateSession;
    }

    /**
     * Метод ищет все роли в базе данных
     *
     * @return список ролей найденых в базе
     */

    @Override
    public List<Role> findAll() {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {
            transaction = session.beginTransaction();
            Query<Role> query = session.createQuery("FROM Role");
            List<Role> roles = query.list();
            transaction.commit();
            return roles;
        } catch (Exception e) {
            LOG.error("Error when finding all roles", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * Метод находит роли по Id
     *
     * @param id id по которому ищется роль
     * @return роль по id
     */

    @Override
    public Role findById(Long id) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {
            transaction = session.beginTransaction();
            Query<Role> query = session.createQuery("FROM Role WHERE id = :role_id");
            query.setParameter("role_id", id);
            Role role = query.getSingleResult();

            transaction.commit();
            return role;
        } catch (Exception e) {
            LOG.error("Error when finding role by id", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод добавляет в базу новые роли
     *
     * @param role роль для добаления
     */

    @Override
    public void create(Role role) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            session.save(role);

            transaction.commit();

        } catch (Exception e) {
            LOG.error("Error when creating new role", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * метод апдейтит данные в базе
     *
     * @param role роль для апдейта
     */

    @Override
    public void update(Role role) {

        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            Query query = session.createQuery("UPDATE Role SET name = :role_name WHERE id = :role_id");
            query.setParameter("role_name", role.getName());
            query.setParameter("role_id", role.getId());
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            LOG.error("Error when updating role", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }

    }

    /**
     * метод удаляет роль из базы
     *
     * @param role роль для удаления
     */

    @Override
    public void remove(Role role) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {

            transaction = session.beginTransaction();
            Query query = session.createQuery("DELETE FROM Role WHERE id = :role_id");
            query.setParameter("role_id", role.getId());
            query.executeUpdate();

            transaction.commit();
        } catch (Exception e) {
            LOG.error("Error when deleting role", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }

    /**
     * Метод для поиска роли по имени в базе
     *
     * @param name имя для поиска роли
     * @return роль найденная по имени в базе
     */

    @Override
    public Role findByName(String name) {
        Transaction transaction = null;

        try (Session session = hibernateSession.getSession()) {
            transaction = session.beginTransaction();
            Query<Role> query = session.createQuery("FROM Role WHERE name = :role_name");
            query.setParameter("role_name", name);
            Role role = query.getSingleResult();

            transaction.commit();
            return role;
        } catch (Exception e) {
            LOG.error("Error when finding role by name", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateDaoException(e.getMessage(), e);
        }
    }
}
