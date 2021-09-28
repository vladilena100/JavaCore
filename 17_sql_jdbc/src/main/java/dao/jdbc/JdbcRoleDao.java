package dao.jdbc;

import dao.DaoRole;
import model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import support.ConnectionManager;

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
     * @return список ролей найденых в базе или пустой лист если есть SQLException
     */

    @Override
    public List<Role> findAll() {

        try (Connection connection = connectionManager.getConnection()){
            connection.setAutoCommit(false);

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery("SELECT * FROM role")){
                List<Role> roles = new ArrayList<>();

                while (resultSet.next()) {
                    final long id = resultSet.getLong("role_id");
                    final  String name = resultSet.getString("role_name");
                    roles.add(new Role(id, name));
                }
                connection.commit();
                LOG.info("find {} roles in db", roles.size());
                return roles;
            } catch (SQLException e) {
                LOG.error("Can`t execute statement", e);
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Message: {}", e.getMessage(), e);
        }
        return Collections.emptyList();
    }

    /**
     * Метод находит роли по Id
     * если роль не найдена возвращается null
     * @param id id по которому ищется роль
     * @return роль по id или null если роль не найдена
     */

    @Override
    public Role findById(Long id) {
        try (Connection connection = connectionManager.getConnection()){
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM role WHERE role_id = ?;")){
                statement.setLong(1, id);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        long roleId = resultSet.getLong("role_id");
                        String name = resultSet.getString("role_name");
                        connection.commit();
                        Role role = new Role(roleId, name);
                        LOG.info("Find role {} by id {}", role, id);
                        return role;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                LOG.error("Can`t execute statement", e);
            }
        } catch (SQLException e) {
            LOG.error("Message: {}", e.getMessage(), e);
        }
        return null;
    }

    /**
     * метод добавляет в базу новые роли
     * @param role роль для добаления
     */

    @Override
    public void create(Role role) {
        try (Connection connection = connectionManager.getConnection()){
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO role(role_id, role_name) VALUES (?, ?);"
            )){
                statement.setLong(1, role.getId());
                statement.setString(2, role.getName());
                statement.execute();
                connection.commit();
                LOG.info("role {} inserted ", role);
            } catch (SQLException e) {
                LOG.error("Can`t execute statement", e);
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Message: {}", e.getMessage(), e);
        }

    }

    /**
     * метод апдейтит данные в базе
     * @param role роль для апдейта
     */

    @Override
    public void update(Role role) {

        try (Connection connection = connectionManager.getConnection()){
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "UPDATE role SET role_name = ? WHERE role_id = ?;"
            )){
                statement.setString(1, role.getName());
                statement.setLong(2, role.getId());
                statement.executeUpdate();
                connection.commit();
                LOG.info("Role {} successfully updated ", role);
            } catch (SQLException e) {
                LOG.error("Can`t execute statement ", e);
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Message: {}", e.getMessage(), e);
        }

    }

    /**
     * метод удаляет роль из базы
     * @param role роль для удаления
     */

    @Override
    public void remove(Role role) {
        try (Connection connection = connectionManager.getConnection()){
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM role WHERE role_name = ?;"
            )){
                statement.setString(1, role.getName());
                statement.executeUpdate();
                connection.commit();
                LOG.info("Role {} removed successfully ", role);
            } catch (SQLException e) {
                LOG.error("Can`t execute statement ", e);
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Message: {}", e.getMessage(), e);
        }
    }

    /**
     * Метод для поиска роли по имени в базе
     * @param name имя для поиска роли
     * @return роль найденная по имени в базе или null если роль не найдена
     */

    @Override
    public Role findByName(String name) {
        try (Connection connection = connectionManager.getConnection()){
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM role WHERE role_neme = ?;"
            )){
                statement.setString(1, name);
                try (ResultSet resultSet = statement.executeQuery()){
                    if (resultSet.next()) {
                        long roleId = resultSet.getLong("role_id");
                        String roleName = resultSet.getString("role_name");
                        connection.commit();
                        Role role = new Role(roleId, roleName);
                        LOG.info("Find role: {} by name {} ", role, name);
                        return role;
                    }
                } catch (SQLException e) {
                    LOG.error("Can`t execute statement ", e);
                    connection.rollback();
                }
            } catch (SQLException e) {
                LOG.error("Can`t prepare statement ", e);
                connection.rollback();
            }
        } catch (SQLException e) {
            LOG.error("Nessage: {} ", e.getMessage(), e);
        }
        return null;
    }
}
