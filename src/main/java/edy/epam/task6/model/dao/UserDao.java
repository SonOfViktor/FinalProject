package edy.epam.task6.model.dao;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.model.builder.UserBuilder;
import edy.epam.task6.model.entity.*;
import edy.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static UserDao instance;

    //CREATE REGEX
    private static final String CREATE_USER = """
            INSERT INTO users (email, login, password, name, surname, 
            discount, balance, registration_date, status_id, role_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    //FIND REGEX
    private static final String FIND_BY_ID = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE user_id=?""";
    private static final String FIND_BY_LOGIN = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE login=?""";
    private static final String FIND_BY_NAME = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE name=?""";
    private static final String FIND_BY_SURNAME = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE surname=?""";
    private static final String FIND_ALL_USERS = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id""";
    private static final String FIND_ALL_BLOCKED_USERS = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE user_statuses.status=?""";
    //UPDATE REGEX
    private static final String UPDATE_LOGIN = "UPDATE users SET users.login=? WHERE user_id=?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password=? WHERE user_id=?";
    private static final String UPDATE_DISCOUNT = "UPDATE users SET discount=? WHERE user_id=?";
    private static final String UPDATE_BALANCE = "UPDATE users SET balance=? WHERE user_id=?";
    private static final String UPDATE_STATUS = "UPDATE users SET status_id=? WHERE user_id=?";

    private UserDao(){}

    public static UserDao getInstance(){
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, parameters.get(FieldName.USER_EMAIL));
            statement.setString(2, parameters.get(FieldName.USER_LOGIN));
            statement.setString(3, parameters.get(FieldName.USER_PASSWORD));
            statement.setString(4, parameters.get(FieldName.USER_NAME));
            statement.setString(5, parameters.get(FieldName.USER_SURNAME));
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setTimestamp(8, Timestamp.valueOf(parameters.get(FieldName.USER_REGISTRATION_DATE)));
            statement.setInt(9, 1);
            statement.setInt(10, 2);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding user.", e);
            throw new DaoException("Error during adding user.", e);
        }
        return result;
    }

    public boolean updateLogin(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_LOGIN)){
            statement.setString(1, parameters.get(FieldName.USER_LOGIN));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating password of user with id = " + userId, e);
            throw new DaoException("Error during updating password of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updatePassword(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)){
            statement.setString(1, parameters.get(FieldName.USER_PASSWORD));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating password of user with id = " + userId, e);
            throw new DaoException("Error during updating password of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateDiscount(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_DISCOUNT)){
            statement.setString(1, parameters.get(FieldName.USER_DISCOUNT));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating discount of user with id = " + userId, e);
            throw new DaoException("Error during updating discount of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateBalance(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE)){
            statement.setString(1, parameters.get(FieldName.USER_BALANCE));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating balance of user with id = " + userId, e);
            throw new DaoException("Error during updating balance of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateStatus(int status_id, Long userId) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)){
            statement.setInt(1, status_id);
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating status of user with id = " + userId, e);
            throw new DaoException("Error during updating status of user with id = " + userId, e);
        }
        return result;
    }

    public Optional<User> findById(Long soughtId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, soughtId.intValue());
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<User> resultUser = Optional.empty();
                if (resultSet.next()) {
                    UserBuilder userBuilder = new UserBuilder();
                    userBuilder.setUserId(resultSet.getLong(FieldName.USER_ID));
                    userBuilder.setEmail(resultSet.getString(FieldName.USER_EMAIL));
                    userBuilder.setLogin(resultSet.getString(FieldName.USER_LOGIN));
                    userBuilder.setPassword(resultSet.getString(FieldName.USER_PASSWORD));
                    userBuilder.setName(resultSet.getString(FieldName.USER_NAME));
                    userBuilder.setSurname(resultSet.getString(FieldName.USER_SURNAME));
                    userBuilder.setDiscount(resultSet.getInt(FieldName.USER_DISCOUNT));
                    userBuilder.setBalance(resultSet.getBigDecimal(FieldName.USER_BALANCE));
                    userBuilder.setRegistrationDate(resultSet.getTimestamp(FieldName.USER_REGISTRATION_DATE));
                    userBuilder.setStatus(UserStatus.valueOf(resultSet.getString(FieldName.USER_STATUS)));
                    userBuilder.setRole(UserRole.valueOf(resultSet.getString(FieldName.USER_ROLE)));
                    resultUser = Optional.of(userBuilder.build());
                }
                return resultUser;
            }
        } catch (SQLException e) {
            logger.error("Error during searching user by ID.", e);
            throw new DaoException("Error during searching user by ID.", e);
        }
    }

    public Optional<User> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()){
                Optional<User> resultUser = Optional.empty();
                if (resultSet.next()) {
                    UserBuilder userBuilder = new UserBuilder();
                    userBuilder.setUserId(resultSet.getLong(FieldName.USER_ID));
                    userBuilder.setEmail(resultSet.getString(FieldName.USER_EMAIL));
                    userBuilder.setLogin(resultSet.getString(FieldName.USER_LOGIN));
                    userBuilder.setPassword(resultSet.getString(FieldName.USER_PASSWORD));
                    userBuilder.setName(resultSet.getString(FieldName.USER_NAME));
                    userBuilder.setSurname(resultSet.getString(FieldName.USER_SURNAME));
                    userBuilder.setDiscount(resultSet.getInt(FieldName.USER_DISCOUNT));
                    userBuilder.setBalance(resultSet.getBigDecimal(FieldName.USER_BALANCE));
                    userBuilder.setRegistrationDate(resultSet.getTimestamp(FieldName.USER_REGISTRATION_DATE));
                    userBuilder.setStatus(UserStatus.valueOf(resultSet.getString(FieldName.USER_STATUS)));
                    userBuilder.setRole(UserRole.valueOf(resultSet.getString(FieldName.USER_ROLE)));
                    resultUser = Optional.of(userBuilder.build());
                }
                return resultUser;
            }
        } catch (SQLException e) {
            logger.error("Error during searching user by login.", e);
            throw new DaoException("Error during searching user by login.", e);
        }
    }

    public List<User> findByName(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()){
                return createUsers(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching user by name.", e);
            throw new DaoException("Error during searching user by name.", e);
        }
    }

    public List<User> findBySurname(String surname) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_SURNAME)) {
            statement.setString(1, surname);
            try (ResultSet resultSet = statement.executeQuery()){
                return createUsers(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching user by surname.", e);
            throw new DaoException("Error during searching user by surname.", e);
        }
    }

    public List<User> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS)) {
            try (ResultSet resultSet = statement.executeQuery()){
                return createUsers(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for all users.", e);
            throw new DaoException("Error during searching for all users.", e);
        }
    }

    public List<User> findAllBlocked() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_BLOCKED_USERS)) {
            statement.setString(1, UserStatus.BLOCKED.name());
            try (ResultSet resultSet = statement.executeQuery()){
                return createUsers(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for all BLOCKED users.", e);
            throw new DaoException("Error during searching for all BLOCKED users.", e);
        }
    }

    private List<User> createUsers(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setUserId(resultSet.getLong(FieldName.USER_ID));
            userBuilder.setEmail(resultSet.getString(FieldName.USER_EMAIL));
            userBuilder.setLogin(resultSet.getString(FieldName.USER_LOGIN));
            userBuilder.setPassword(resultSet.getString(FieldName.USER_PASSWORD));
            userBuilder.setName(resultSet.getString(FieldName.USER_NAME));
            userBuilder.setSurname(resultSet.getString(FieldName.USER_SURNAME));
            userBuilder.setDiscount(resultSet.getInt(FieldName.USER_DISCOUNT));
            userBuilder.setBalance(resultSet.getBigDecimal(FieldName.USER_BALANCE));
            userBuilder.setRegistrationDate(resultSet.getTimestamp(FieldName.USER_REGISTRATION_DATE));
            userBuilder.setStatus(UserStatus.valueOf(resultSet.getString(FieldName.USER_STATUS)));
            userBuilder.setRole(UserRole.valueOf(resultSet.getString(FieldName.USER_ROLE)));
            userList.add(userBuilder.build());
        }
        return userList;
    }
}
