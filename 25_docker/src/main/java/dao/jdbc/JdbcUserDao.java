package dao.jdbc;

import dao.DaoUser;
import exception.DataDaoException;
import model.Role;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import support.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * JdbcUserDao
 *
 * @author Vladilena Vasilieva
 */

public class JdbcUserDao implements DaoUser {

    private static final Logger LOG = LogManager.getLogger(JdbcUserDao.class);

    public static final Long DEFAULT_ROLE = 1L;

    private final ConnectionManager connectionManager;

    public JdbcUserDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Метод находит пользователей по id
     *
     * @param id id для поиска пользователя
     * @return пользователь из базу или null если пользователь не найден
     */

    @Override
    public User findById(Long id) {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.id, u.login, u.password, u.email, u.first_name, u.last_name, u.birthday, r.role_id, r.role_name " +
                             "FROM \"user\" AS u INNER JOIN role AS r ON r.role_id = u.role_id WHERE u.id = ?")
             ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthday = resultSet.getDate("birthday");
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                return new User(userId, login, password, email,
                        firstName, lastName, birthday,
                        new Role(roleId, roleName));
            }
            return null;
        } catch (SQLException e) {
            LOG.error("Can`t find user by id", e);
            throw new DataDaoException("Error find user by id");
        }
    }

    /**
     * метод создает пользователя в базе
     *
     * @param user пользователь которого нужно добавить в базу
     */

    @Override
    public void create(User user) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO \"user\"" +
                             "(login, password, email, first_name, last_name, birthday, role_id) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)"
             )) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setDate(6, user.getBirthday());
            statement.setLong(7, Optional.ofNullable(user.getRole())
                    .map(Role::getId)
                    .orElse(DEFAULT_ROLE));
            statement.execute();
        } catch (SQLException e) {
            LOG.error("Can`t created user", e);
            throw new DataDaoException("Error created user");
        }
    }

    /**
     * метод апдейтит пользователя по id в базе
     *
     * @param user пользователь для апдейта в базе
     */

    @Override
    public void update(User user) {
        LOG.info("user: {}", user);
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE \"user\" SET login = ?, password = ?, email = ?, first_name = ?, last_name = ?, birthday = ?, role_id = ? WHERE id = ?;"
             )) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setDate(6, user.getBirthday());
            statement.setLong(7, Optional.ofNullable(user.getRole())
                    .map(Role::getId)
                    .orElse(DEFAULT_ROLE));
            statement.setLong(8, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error("Can`t update user", e);
            throw new DataDaoException("Error update user");
        }
    }

    /**
     * метод удаляет пользователя из базы
     *
     * @param user пользователь для удаления из базы по уникальным полям email и пароль
     */

    @Override
    public void remove(User user) {
        try (Connection connection = connectionManager.getTransactionalConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "DELETE FROM \"user\" WHERE id = ?;"
             )) {
                statement.setLong(1, user.getId());
                statement.executeUpdate();
                connection.commit();
        } catch (SQLException e) {
            LOG.error("Can`t get connection to DB", e);
            throw new DataDaoException("Error get connection to DB");
        }

    }

    /**
     * метод находит всех пользователей в базе
     *
     * @return список пользователей
     */

    @Override
    public List<User> findAll() {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.id, u.login, u.password, u.email, u.first_name, u.last_name, u.birthday, r.role_id, r.role_name " +
                             "FROM \"user\" AS u INNER JOIN role AS r ON u.role_id = r.role_id ");
             ResultSet resultSet = statement.executeQuery()
        ) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthday = resultSet.getDate("birthday");
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                users.add(new User(userId, login, password, email,
                        firstName, lastName, birthday,
                        new Role(roleId, roleName)));
            }
            return users;
        } catch (SQLException e) {
            LOG.error("Can`t find all users", e);
            throw new DataDaoException("Error find all users");
        }
    }

    /**
     * метод находит пользователя по логину в базе
     *
     * @param login логин по которому производится поиск
     * @return пользователь из базы или null если пользователь не найден
     */

    @Override
    public User findByLogin(String login) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.id, u.login, u.password, u.email, u.first_name, u.last_name, u.birthday, r.role_id, r.role_name " +
                             "FROM \"user\" AS u " +
                             "INNER JOIN role AS r ON r.role_id = u.role_id " +
                             "WHERE u.login = ? ")
             ) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String userLogin = resultSet.getString("login");
                String password = resultSet.getString("password");
                String email = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthday = resultSet.getDate("birthday");
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                return new User(userId, userLogin, password, email, firstName, lastName,
                        birthday, new Role(roleId, roleName));
            }
        } catch (SQLException e) {
            LOG.error("Can`t find users by login", e);
            throw new DataDaoException("Error find user by login");
        }
        return null;
    }

    /**
     * метод находит пользователя по email в базе
     *
     * @param email email по которому производится поиск
     * @return пользователь из базы или null если пользователь не найден
     */

    @Override
    public User findByEmail(String email) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT u.id, u.login, u.password, u.email, u.first_name, u.last_name, u.birthday, r.role_id, r.role_name " +
                             "FROM \"user\" AS u INNER JOIN role AS r ON r.role_id = u.role_id WHERE u.email = ? ")
             ) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long userId = resultSet.getLong("id");
                String userLogin = resultSet.getString("login");
                String password = resultSet.getString("password");
                String userEmail = resultSet.getString("email");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                Date birthday = resultSet.getDate("birthday");
                Long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                return new User(userId, userLogin, password, userEmail,
                        firstName, lastName, birthday,
                        new Role(roleId, roleName));
            }
        } catch (SQLException e) {
            LOG.error("Can`t find user by email", e);
            throw new DataDaoException("Error find user by email");
        }
        return null;
    }
}
