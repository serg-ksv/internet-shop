package com.ksv.internetshop.dao.jdbc;

import com.ksv.internetshop.dao.UserDao;
import com.ksv.internetshop.exception.DataProcessingException;
import com.ksv.internetshop.lib.Dao;
import com.ksv.internetshop.model.Role;
import com.ksv.internetshop.model.User;
import com.ksv.internetshop.util.ConnectionUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Dao
public class UserDaoJdbcImp implements UserDao {
    @Override
    public Optional<User> findByLogin(String login) {
        var query = "SELECT * FROM users WHERE login = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, login);
            var resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = getUserFromResultSet(resultSet);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve user. " + e.getMessage());
        }
    }

    @Override
    public User create(User user) {
        var query = "INSERT INTO users (name, email, login, password, salt)"
                + "VALUES (?, ?, ?, ?, ?)";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBytes(5, user.getSalt());
            preparedStatement.executeUpdate();
            var resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                var userId = resultSet.getLong(1);
                user.setId(userId);
            }
            setRolesForUser(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't create user. " + e.getMessage());
        }
    }

    @Override
    public Optional<User> get(Long userId) {
        var query = "SELECT * FROM users WHERE user_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var user = getUserFromResultSet(resultSet);
                return Optional.of(user);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve user. " + e.getMessage());
        }
    }

    @Override
    public List<User> getAll() {
        var query = "SELECT * FROM users";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                var user = getUserFromResultSet(resultSet);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't retrieve users. " + e.getMessage());
        }
    }

    @Override
    public User update(User user) {
        var query = "UPDATE users SET name = ?, email = ?, login = ?, password = ?, salt = ?"
                + "WHERE user_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setBytes(5, user.getSalt());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
            deleteUserFromUsersRoles(user.getId());
            setRolesForUser(user);
            return user;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't update user. " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long userId) {
        var query = "DELETE FROM users WHERE user_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            deleteUserFromUsersRoles(userId);
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user. " + e.getMessage());
        }
    }

    private User getUserFromResultSet(ResultSet resultSet) throws SQLException {
        var userId = resultSet.getLong("user_id");
        var userName = resultSet.getString("name");
        var email = resultSet.getString("email");
        var login = resultSet.getString("login");
        var password = resultSet.getString("password");
        var salt = resultSet.getBytes("salt");
        var user = new User(userName, email, login, password);
        user.setSalt(salt);
        user.setId(userId);
        user.setRoles(getUsersRoles(userId));
        return user;
    }

    private Set<Role> getUsersRoles(Long userId) {
        var query = "SELECT role_id, role_name FROM users_roles "
                + "JOIN roles USING (role_id) WHERE user_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, userId);
            var resultSet = prepareStatement.executeQuery();
            Set<Role> roles = new HashSet<>();
            if (resultSet.next()) {
                var role = Role.of(resultSet.getString("role_name"));
                role.setId(resultSet.getLong("role_id"));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user's roles. " + e.getMessage());
        }
    }

    private void setRolesForUser(User user) {
        var getRoleIdQuery = "SELECT role_id FROM roles WHERE role_name = ?";
        var usersRolesQuery = "INSERT INTO users_roles (user_id, role_id)"
                            + "VALUES (?, ?)";
        try (var connection = ConnectionUtil.getConnection()) {
            var getRoleIdStatement = connection.prepareStatement(getRoleIdQuery);
            var usersRolesStatement = connection.prepareStatement(usersRolesQuery);
            for (var role : user.getRoles()) {
                getRoleIdStatement.setString(1, role.getRoleName().toString());
                var resultSet = getRoleIdStatement.executeQuery();
                while (resultSet.next()) {
                    role.setId(resultSet.getLong("role_id"));
                }
                usersRolesStatement.setLong(1, user.getId());
                usersRolesStatement.setLong(2, role.getId());
                usersRolesStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't set roles for user. " + e.getMessage());
        }
    }

    private void deleteUserFromUsersRoles(Long userId) {
        var query = "DELETE FROM users_roles WHERE user_id = ?";
        try (var connection = ConnectionUtil.getConnection()) {
            var prepareStatement = connection.prepareStatement(query);
            prepareStatement.setLong(1, userId);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't delete user from users_roles. "
                    + e.getMessage());
        }
    }
}
