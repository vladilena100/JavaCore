package com.solutions.vasylieva.dao.jdbc;

import com.solutions.vasylieva.dao.DaoRole;
import com.solutions.vasylieva.exception.DataDaoException;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solutions.vasylieva.support.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JdbcRoleDao
 *
 * @author Vladilena Vasilieva
 */

public class JdbcRoleDao implements DaoRole {

    private static final Logger LOG = LogManager.getLogger(JdbcRoleDao.class);

    private final ConnectionManager connectionManager;

    public JdbcRoleDao(ConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    /**
     * Метод ищет все роли в базе данных
     *
     * @return список ролей найденых в базе или пустой лист если есть SQLException
     */

    @Override
    public List<Role> findAll() {

        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM role");
             ResultSet resultSet = statement.executeQuery()) {
            List<Role> roles = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong("role_id");
                final String name = resultSet.getString("role_name");
                roles.add(new Role(id, name));
            }
            LOG.info("find {} roles in db", roles.size());
            return roles;
        } catch (SQLException e) {
            LOG.error("Can`t execute statement", e);
            throw new DataDaoException("Error find all roles");
        }
    }

    /**
     * Метод находит роли по Id
     * если роль не найдена возвращается null
     *
     * @param id id по которому ищется роль
     * @return роль по id или null если роль не найдена
     */

    @Override
    public Role findById(Long id) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM role WHERE role_id = ?;")) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                long roleId = resultSet.getLong("role_id");
                String name = resultSet.getString("role_name");
                Role role = new Role(roleId, name);
                LOG.info("Find role {} by id {}", role, id);
                return role;
            }
            return null;
        } catch (SQLException e) {
            LOG.error("Can`t find role by id", e);
            throw new DataDaoException("Error find role by id");
        }
    }

    /**
     * метод добавляет в базу новые роли
     *
     * @param role роль для добаления
     */

    @Override
    public void create(Role role) {
        try (Connection connection = connectionManager.getTransactionalConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO role(role_name) VALUES (?)")) {

            statement.setString(1, role.getName());
            statement.execute();
            connection.commit();
            LOG.info("role {} inserted ", role);

        } catch (SQLException e) {
            LOG.error("Can`t get connection to DB", e);
            throw new DataDaoException("Can`t execute statement");
        }
    }

    /**
     * метод апдейтит данные в базе
     *
     * @param role роль для апдейта
     */

    @Override
    public void update(Role role) {

        try (Connection connection = connectionManager.getTransactionalConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "UPDATE role SET role_name = ? WHERE role_id = ?;"
             )) {
                statement.setString(1, role.getName());
                statement.setLong(2, role.getId());
                statement.executeUpdate();
                connection.commit();
                LOG.info("Role {} successfully updated ", role);
        } catch (SQLException e) {
            LOG.error("Can`t get connection to DB", e);
            throw new DataDaoException("Error created connection");
        }

    }

    /**
     * метод удаляет роль из базы
     *
     * @param role роль для удаления
     */

    @Override
    public void remove(Role role) {
        try (Connection connection = connectionManager.getTransactionalConnection();
             PreparedStatement statementRole = connection.prepareStatement(
                     "DELETE FROM role WHERE role_id = ?;");
             PreparedStatement statementUser = connection.prepareStatement("SELECT * FROM \"user\" WHERE role_id = ?")) {

                statementRole.setLong(1, role.getId());
                statementUser.setLong(1, role.getId());
                ResultSet resultSet = statementUser.executeQuery();
                if (resultSet.next()) {
                    LOG.error("Can`t delete role.");
                    throw new DataDaoException("Error remove role, user exist this role");
                }
                statementRole.executeUpdate();
                connection.commit();
                LOG.info("Role {} removed successfully ", role);
        } catch (SQLException e) {
            LOG.error("Can`t get connection to DB", e);
            throw new DataDaoException("Error created connection");
        }
    }

    /**
     * Метод для поиска роли по имени в базе
     *
     * @param name имя для поиска роли
     * @return роль найденная по имени в базе или null если роль не найдена
     */

    @Override
    public Role findByName(String name) {
        try (Connection connection = connectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT * FROM role WHERE role_name = ?;");
             ResultSet resultSet = statement.executeQuery()) {
            statement.setString(1, name);
            if (resultSet.next()) {
                long roleId = resultSet.getLong("role_id");
                String roleName = resultSet.getString("role_name");
                connection.commit();
                Role role = new Role(roleId, roleName);
                LOG.info("Find role: {} by name {} ", role, name);
                return role;
            }
            return null;
        } catch (SQLException e) {
            LOG.error("Can`t find role by name", e);
            throw new DataDaoException("Error find role by name");
        }
    }
}
