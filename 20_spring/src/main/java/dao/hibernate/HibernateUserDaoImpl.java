package dao.hibernate;

import dao.DaoUser;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JdbcUserDao
 *
 * @author Vladilena Vasilieva
 */

@Repository
@Transactional
public class HibernateUserDaoImpl implements DaoUser {

    @Autowired
    private final SessionFactory sessionFactory;

    public HibernateUserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * Метод находит пользователей по id
     *
     * @param id id для поиска пользователя
     * @return пользователь из базы
     */

    @Override
    public User findById(Long id) {

        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE u.id = :id");
        query.setParameter("id", id);

        return query.uniqueResult();
    }

    /**
     * метод создает пользователя в базе
     *
     * @param user пользователь которого нужно добавить в базу
     */

    @Override
    public void create(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    /**
     * метод апдейтит пользователя по id в базе
     *
     * @param user пользователь для апдейта в базе
     */

    @Override
    public void update(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    /**
     * метод удаляет пользователя из базы
     *
     * @param user пользователь для удаления из базы
     */

    @Override
    public void remove(User user) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("DELETE FROM User WHERE id = :id");
        query.setParameter("id", user.getId());
        query.executeUpdate();
    }

    /**
     * метод находит всех пользователей в базе
     *
     * @return список пользователей
     */

    @Override
    public List<User> findAll() {

        Session session = sessionFactory.getCurrentSession();
        Query<User> userQuery = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r");
        return userQuery.getResultList();
    }

    /**
     * метод находит пользователя по логину в базе
     *
     * @param login логин по которому производится поиск
     * @return пользователь из базы
     */

    @Override
    public User findByLogin(String login) {

        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE u.login = :login");
        query.setParameter("login", login);
        return query.uniqueResult();
    }

    /**
     * метод находит пользователя по email в базе
     *
     * @param email email по которому производится поиск
     * @return пользователь из базы
     */

    @Override
    public User findByEmail(String email) {

        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("SELECT u FROM User u JOIN FETCH u.role r WHERE u.email = :email");
        query.setParameter("email", email);
        return query.uniqueResult();
    }
}
