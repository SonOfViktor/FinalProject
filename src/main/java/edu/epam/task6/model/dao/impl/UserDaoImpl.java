package edu.epam.task6.model.dao.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.builder.UserBuilder;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.UserDao;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.entity.UserStatus;
import edu.epam.task6.model.entity.*;
import edu.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger logger = LogManager.getLogger();

    private static UserDaoImpl instance;

    @Language("SQL")
    //CREATE REGEX
    private static final String CREATE_USER = """
            INSERT INTO users (email, login, password, name, surname, 
            discount, balance, registration_date, average_rating, status_id, role_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    //FIND REGEX
    private static final String FIND_BY_ID = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, users.average_rating, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE user_id=?""";
    private static final String FIND_BY_LOGIN = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, users.average_rating, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE login=?""";
    private static final String FIND_PASSWORD_BY_LOGIN = "SELECT password FROM users WHERE login=?";
    private static final String FIND_BY_NAME = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, users.average_rating, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE name=?""";
    private static final String FIND_BY_SURNAME = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, users.average_rating, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE surname=?""";
    private static final String FIND_BY_STATUS = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, users.average_rating, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            WHERE user_statuses.status=?""";
    private static final String FIND_ALL_USERS = """
            SELECT user_id, email, login, password, name, surname, 
            discount, balance, registration_date, users.average_rating, user_statuses.status, user_roles.role
            FROM users
            JOIN user_statuses ON users.status_id = user_statuses.status_id
            JOIN user_roles ON users.role_id = user_roles.role_id
            ORDER BY user_id ASC""";
    //UPDATE REGEX
    private static final String UPDATE_EMAIL = "UPDATE users SET email=? WHERE user_id=?";
    private static final String UPDATE_PASSWORD = "UPDATE users SET password=? WHERE user_id=?";
    private static final String UPDATE_NAME = "UPDATE users SET name=? WHERE user_id=?";
    private static final String UPDATE_SURNAME = "UPDATE users SET surname=? WHERE user_id=?";
    private static final String UPDATE_DISCOUNT = "UPDATE users SET discount=? WHERE user_id=?";
    private static final String UPDATE_BALANCE = "UPDATE users SET balance=? WHERE user_id=?";
    private static final String UPDATE_STATUS = "UPDATE users SET status_id=? WHERE user_id=?";
    private static final String UPDATE_AVERAGE_RATING = "UPDATE users SET average_rating=? WHERE user_id=?";

    private UserDaoImpl(){}

    public static UserDaoImpl getInstance(){
        if (instance == null) {
            instance = new UserDaoImpl();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_USER)) {
            statement.setString(1, parameters.get(ColumnName.USER_EMAIL));
            statement.setString(2, parameters.get(ColumnName.USER_LOGIN));
            statement.setString(3, parameters.get(ColumnName.USER_PASSWORD));
            statement.setString(4, parameters.get(ColumnName.USER_NAME));
            statement.setString(5, parameters.get(ColumnName.USER_SURNAME));
            statement.setInt(6, 0);
            statement.setInt(7, 0);
            statement.setTimestamp(8, Timestamp.valueOf(parameters.get(ColumnName.USER_REGISTRATION_DATE)));
            statement.setDouble(9, 0);
            statement.setInt(10, 3);
            statement.setInt(11, 2);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding user.", e);
            throw new DaoException("Error during adding user.", e);
        }
        return result;
    }

    public boolean updateEmail(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_EMAIL)){
            statement.setString(1, parameters.get(ColumnName.USER_EMAIL));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating email of user with id = " + userId, e);
            throw new DaoException("Error during updating email of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updatePassword(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PASSWORD)){
            statement.setString(1, parameters.get(ColumnName.USER_PASSWORD));
            statement.setLong(2, userId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during updating password of user with id = " + userId, e);
            throw new DaoException("Error during updating password of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateName(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_NAME)){
            statement.setString(1, parameters.get(ColumnName.USER_NAME));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating name of user with id = " + userId, e);
            throw new DaoException("Error during updating name of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateSurname(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_SURNAME)){
            statement.setString(1, parameters.get(ColumnName.USER_SURNAME));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating surname of user with id = " + userId, e);
            throw new DaoException("Error during updating surname of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateDiscount(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_DISCOUNT)){
            statement.setString(1, parameters.get(ColumnName.USER_DISCOUNT));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating discount of user with id = " + userId, e);
            throw new DaoException("Error during updating discount of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateBalance(Map<String, String> parameters, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BALANCE)){
            statement.setString(1, parameters.get(ColumnName.USER_BALANCE));
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating balance of user with id = " + userId, e);
            throw new DaoException("Error during updating balance of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateStatus(int statusId, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)){
            statement.setInt(1, statusId);
            statement.setLong(2, userId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating status of user with id = " + userId, e);
            throw new DaoException("Error during updating status of user with id = " + userId, e);
        }
        return result;
    }

    public boolean updateAverageRating(double grade, Long userId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_AVERAGE_RATING)){
            statement.setDouble(1, grade);
            statement.setLong(2, userId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e){
            logger.error("Error during updating average rating of user with id = " + userId, e);
            throw new DaoException("Error during updating average rating of user with id = " + userId, e);
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
                    userBuilder.setUserId(resultSet.getLong(ColumnName.USER_ID));
                    userBuilder.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
                    userBuilder.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                    userBuilder.setName(resultSet.getString(ColumnName.USER_NAME));
                    userBuilder.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
                    userBuilder.setDiscount(resultSet.getInt(ColumnName.USER_DISCOUNT));
                    userBuilder.setBalance(resultSet.getBigDecimal(ColumnName.USER_BALANCE));
                    Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.USER_REGISTRATION_DATE));
                    LocalDateTime localDateTime = LocalDateTime.of(
                            time.toLocalDateTime().toLocalDate(),
                            time.toLocalDateTime().toLocalTime());
                    userBuilder.setRegistrationDate(localDateTime);
                    userBuilder.setAverageRating(resultSet.getDouble(ColumnName.USER_AVERAGE_RATING));
                    userBuilder.setStatus(UserStatus.valueOf(resultSet.getString(ColumnName.USER_STATUS)));
                    userBuilder.setRole(UserRole.valueOf(resultSet.getString(ColumnName.USER_ROLE)));
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
                    userBuilder.setUserId(resultSet.getLong(ColumnName.USER_ID));
                    userBuilder.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
                    userBuilder.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
                    userBuilder.setName(resultSet.getString(ColumnName.USER_NAME));
                    userBuilder.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
                    userBuilder.setDiscount(resultSet.getInt(ColumnName.USER_DISCOUNT));
                    userBuilder.setBalance(resultSet.getBigDecimal(ColumnName.USER_BALANCE));
                    Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.USER_REGISTRATION_DATE));
                    LocalDateTime localDateTime = LocalDateTime.of(
                            time.toLocalDateTime().toLocalDate(),
                            time.toLocalDateTime().toLocalTime());
                    userBuilder.setRegistrationDate(localDateTime);
                    userBuilder.setAverageRating(resultSet.getDouble(ColumnName.USER_AVERAGE_RATING));
                    userBuilder.setStatus(UserStatus.valueOf(resultSet.getString(ColumnName.USER_STATUS)));
                    userBuilder.setRole(UserRole.valueOf(resultSet.getString(ColumnName.USER_ROLE)));
                    resultUser = Optional.of(userBuilder.build());
                }
                return resultUser;
            }
        } catch (SQLException e) {
            logger.error("Error during searching user by login.", e);
            throw new DaoException("Error during searching user by login.", e);
        }
    }

    public Optional<String> findPasswordByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_PASSWORD_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    return Optional.of(resultSet.getString(ColumnName.USER_PASSWORD));
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            logger.error("Error during searching password by login.", e);
            throw new DaoException("Error during searching password by login.", e);
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

    public List<User> findByStatus(String status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_STATUS)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                return createUsers(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for all users with status: " + status, e);
            throw new DaoException("Error during searching for all users with status: " + status, e);
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

    private List<User> createUsers(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            UserBuilder userBuilder = new UserBuilder();
            userBuilder.setUserId(resultSet.getLong(ColumnName.USER_ID));
            userBuilder.setEmail(resultSet.getString(ColumnName.USER_EMAIL));
            userBuilder.setLogin(resultSet.getString(ColumnName.USER_LOGIN));
            userBuilder.setName(resultSet.getString(ColumnName.USER_NAME));
            userBuilder.setSurname(resultSet.getString(ColumnName.USER_SURNAME));
            userBuilder.setDiscount(resultSet.getInt(ColumnName.USER_DISCOUNT));
            userBuilder.setBalance(resultSet.getBigDecimal(ColumnName.USER_BALANCE));
            Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.USER_REGISTRATION_DATE));
            LocalDateTime localDateTime = LocalDateTime.of(
                    time.toLocalDateTime().toLocalDate(),
                    time.toLocalDateTime().toLocalTime());
            userBuilder.setRegistrationDate(localDateTime);
            userBuilder.setAverageRating(resultSet.getDouble(ColumnName.USER_AVERAGE_RATING));
            userBuilder.setStatus(UserStatus.valueOf(resultSet.getString(ColumnName.USER_STATUS)));
            userBuilder.setRole(UserRole.valueOf(resultSet.getString(ColumnName.USER_ROLE)));
            userList.add(userBuilder.build());
        }
        return userList;
    }
}
