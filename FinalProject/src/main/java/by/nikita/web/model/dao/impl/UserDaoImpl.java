package by.nikita.web.model.dao.impl;

import by.nikita.web.exception.ConnectionDataBaseException;
import by.nikita.web.exception.DaoException;
import by.nikita.web.model.dao.ConnectionPool;
import by.nikita.web.model.dao.UserDAO;
import by.nikita.web.model.dao.query.SqlBookRequest;
import by.nikita.web.model.dao.query.SqlUserRequest;

import by.nikita.web.model.entity.Book;
import by.nikita.web.model.entity.User;
import by.nikita.web.model.entity.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * The {@code UserDaoImpl} class represents  User Dao Implements.
 *
 * @author Belyaev Nikita
 * @version 1.0
 */
public class UserDaoImpl implements UserDAO {

    private static final UserDaoImpl instance = new UserDaoImpl();

    private UserDaoImpl() {
    }

    /**
     * get instance
     *
     * @return the instance
     */
    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void addUser(String login, String password) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.ADD_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            statement.executeUpdate();//command from update DB

        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public List<User> getAllUsers() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> users;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlUserRequest.SELECT_ALL_USERS);
            users = readAllUsers(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public User findUser(String login, String password) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        User users;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.LOOK_FOR_USER)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            users = readUsers(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public User findUserByName(String login) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        User users;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.LOOK_FOR_USER_NAME)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            users = readUsers(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public List<User> findUserByNameSort(String name) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> users;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.LOOK_FOR_USER_INFO_BY_NAME)) {
            statement.setString(1, name + "%");
            ResultSet resultSet = statement.executeQuery();
            users = readAllUsersInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public List<User> getAllUsersInfo() throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> users;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlUserRequest.SELECT_ALL_USERS_INFORMATION);
            users = readAllUsersInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public User findUserById(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        User users;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.LOOK_FOR_USER_BY_ID)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            users = readUsers(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    private User readUsers(ResultSet resultSet) throws SQLException {
        User users = null;
        while (resultSet.next()) {
            String login = resultSet.getString(1);
            String password = resultSet.getString(2);
            UserRole role = UserRole.valueOf(resultSet.getString(3).toUpperCase(Locale.ROOT));
            int id = resultSet.getInt(4);
            users = new User(login, password, role, id);
        }
        return users;
    }

    private List<User> readAllUsers(ResultSet resultSet) throws SQLException {
        User users;
        List<User> listWithUsers = new ArrayList<>();
        while (resultSet.next()) {
            String login = resultSet.getString(1);
            String password = resultSet.getString(2);
            UserRole role = UserRole.valueOf(resultSet.getString(3).toUpperCase(Locale.ROOT));
            int id = resultSet.getInt(4);
            users = new User(login, password, role, id);
            listWithUsers.add(users);
        }
        return listWithUsers;
    }

    @Override
    public void addUserInfo(int idUser, String name, String dateRegistration, int rating, String email) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.ADD_USER_INFO)) {

            statement.setInt(1, idUser);
            statement.setString(2, name);
            statement.setString(3, dateRegistration);
            statement.setInt(4, rating);
            statement.setString(5, email);
            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void deleteUser(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.DELETE_USER_BY_ID)) {
            statement.setInt(1, idUser);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void deleteUserInfo(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.DELETE_USER_INFO_BY_ID)) {
            statement.setInt(1, idUser);
            statement.execute();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void updateUserRole(int idUser, int idRole) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.UPDATE_ROLE)) {
            statement.setInt(1, idRole);
            statement.setInt(2, idUser);
            statement.executeUpdate();//command from update DB

        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public User findUserInfoById(int idUser) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        User users;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.LOOK_FOR_USER_INFO)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            users = readUsersIfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public List<User> findUserInfoByName(String name) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> users;
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.LOOK_FOR_USER_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            users = readAllUsers(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    private User readUsersIfo(ResultSet resultSet) throws SQLException {
        User users = null;
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int rating = resultSet.getInt(4);
            String dateRegistration = resultSet.getString(3);
            String email = resultSet.getString(6);
            String picture = resultSet.getString(5);
            users = new User(id, name, dateRegistration, rating, email, picture);
        }
        return users;
    }

    private List<User> readAllUsersInfo(ResultSet resultSet) throws SQLException {
        User user;
        List<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            int rating = resultSet.getInt(4);
            String dateRegistration = resultSet.getString(3);
            String email = resultSet.getString(6);
            String picture = resultSet.getString(5);
            user = new User(id, name, dateRegistration, rating, email, picture);
            users.add(user);
        }
        return users;
    }

    @Override
    public void updatePicture(int idUser, String picture) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.UPDATE_PICTURE)) {
            statement.setString(1, picture);
            statement.setInt(2, idUser);
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public void updateUserInfo(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.UPDATE_USER_INFO)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getName());
            statement.setInt(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }

    @Override
    public List<User> getAllUsersBySort(String sort) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        List<User> users;
        try (Connection connection = pool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SqlUserRequest.SORT_ALL_USERS + sort);
            users = readAllUsersInfo(resultSet);
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
        return users;
    }

    @Override
    public void updateUserPassword(User user) throws DaoException {
        ConnectionPool pool = ConnectionPool.getInstance();
        try (Connection connection = pool.getConnection();
             PreparedStatement statement = connection.prepareStatement(SqlUserRequest.UPDATE_PASSWORD)) {
            statement.setString(1, user.getPassword());
            statement.setInt(2, user.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            throw new DaoException("We have problem with connection to db", ex);
        } catch (ConnectionDataBaseException e) {
            throw new DaoException("We have problem with connection pool", e);
        }
    }


}
