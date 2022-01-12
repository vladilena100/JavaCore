package dao.hibernate;

import dao.DaoRole;
import exception.HibernateDaoException;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import support.HibernateSession;

import java.util.List;

/**
 * JdbcRoleDao
 *
 * @author Vladilena Vasilieva
 */

@Repository
@Transactional
public class HibernateRoleDaoImpl implements DaoRole {

    private static final Logger LOG = LogManager.getLogger(HibernateRoleDaoImpl.class);

    @Autowired
    private final SessionFactory sessionFactory;

    public HibernateRoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод ищет все роли в базе данных
     *
     * @return список ролей найденых в базе
     */

    @Override
    public List<Role> findAll() {

        Session session = sessionFactory.getCurrentSession();
        List<Role> roles = session.createQuery("FROM Role", Role.class).getResultList();
        return roles;
    }

    /**
     * Метод находит роли по Id
     *
     * @param id id по которому ищется роль
     * @return роль по id
     */

    @Override
    public Role findById(Long id) {

        Session session = sessionFactory.getCurrentSession();
            Query<Role> query = session.createQuery("FROM Role WHERE id = :role_id");
            query.setParameter("role_id", id);
            Role role = query.getSingleResult();
            return role;
    }

    /**
     * метод добавляет в базу новые роли
     *
     * @param role роль для добаления
     */

    @Override
    public void create(Role role) {


        Session session = sessionFactory.getCurrentSession();


            session.save(role);


    }

    /**
     * метод апдейтит данные в базе
     *
     * @param role роль для апдейта
     */

    @Override
    public void update(Role role) {

        Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery("UPDATE Role SET name = :role_name WHERE id = :role_id");
            query.setParameter("role_name", role.getName());
            query.setParameter("role_id", role.getId());
            query.executeUpdate();

    }

    /**
     * метод удаляет роль из базы
     *
     * @param role роль для удаления
     */

    @Override
    public void remove(Role role) {

        Session session = sessionFactory.getCurrentSession();

            Query query = session.createQuery("DELETE FROM Role WHERE id = :role_id");
            query.setParameter("role_id", role.getId());
            query.executeUpdate();
    }

    /**
     * Метод для поиска роли по имени в базе
     *
     * @param name имя для поиска роли
     * @return роль найденная по имени в базе
     */

    @Override
    public Role findByName(String name) {

        Session session = sessionFactory.getCurrentSession();

            Query<Role> query = session.createQuery("FROM Role WHERE name = :role_name");
            query.setParameter("role_name", name);
            Role role = query.getSingleResult();

            return role;
    }
}
