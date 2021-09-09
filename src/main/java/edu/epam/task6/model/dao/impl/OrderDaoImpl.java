package edu.epam.task6.model.dao.impl;

import edu.epam.task6.model.builder.OrderBuilder;
import edu.epam.task6.model.dao.OrderDao;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.*;
import edu.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderDaoImpl implements OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static OrderDaoImpl instance;

    @Language("SQL")
    //CREATE REGEX
    private static final String CREATE_ORDER = """
            INSERT INTO orders (paid, registration_date, users_user_id, 
            order_status_id, tattoos_tattoo_id)
            VALUES (?, ?, ?, ?, ?)""";
    //FIND REGEX
    private static final String FIND_BY_ORDER_ID = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            WHERE order_id=?""";
    private static final String FIND_BY_USER_LOGIN = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            WHERE users.login=?""";
    private static final String FIND_BY_STATUS = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            WHERE order_statuses.status=?""";
    private static final String FIND_BY_ORDER_ID_PERSONAL = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            WHERE order_id=? AND users.login=?""";
    private static final String FIND_BY_STATUS_PERSONAL = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            WHERE order_statuses.status=? AND users.login=?""";
    private static final String FIND_ALL_ORDERS = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            ORDER BY order_id ASC""";
    //UPDATE REGEX
    private static final String UPDATE_STATUS = "UPDATE orders SET orders.order_status_id=? WHERE order_id=?";

    private OrderDaoImpl(){}

    public static OrderDaoImpl getInstance(){
        if (instance == null) {
            instance = new OrderDaoImpl();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {
            statement.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong(parameters.get(ColumnName.ORDERS_PAID))));
            statement.setTimestamp(2, Timestamp.valueOf(parameters.get(ColumnName.ORDERS_REGISTRATION_DATE)));
            statement.setLong(3, Long.parseLong(parameters.get(ColumnName.ORDERS_USER_ID)));
            statement.setLong(4, 1);
            statement.setLong(5, Long.parseLong(parameters.get(ColumnName.ORDERS_TATTOO_ID)));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding tattoo.", e);
            throw new DaoException("Error during adding tattoo.", e);
        }
        return result;
    }

    public boolean updateStatus(int statusId, Long orderId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)) {
            statement.setLong(1, statusId);
            statement.setLong(2, orderId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating status of order with id = " + orderId, e);
            throw new DaoException("Error during updating status of order with id = " + orderId, e);
        }
        return result;
    }

    public Optional<Order> findById(Long id) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ORDER_ID)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Order> resultOrder = Optional.empty();
                if (resultSet.next()) {
                    OrderBuilder orderBuilder = new OrderBuilder();
                    orderBuilder.setOrderId(resultSet.getLong(ColumnName.ORDERS_ID));
                    orderBuilder.setPaid(resultSet.getBigDecimal(ColumnName.ORDERS_PAID));
                    Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.ORDERS_REGISTRATION_DATE));
                    LocalDateTime localDateTime = LocalDateTime.of(
                            time.toLocalDateTime().toLocalDate(),
                            time.toLocalDateTime().toLocalTime());
                    orderBuilder.setRegistrationDate(localDateTime);
                    orderBuilder.setUserLogin(resultSet.getString(ColumnName.ORDERS_USER_LOGIN));
                    orderBuilder.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ColumnName.ORDERS_STATUS)));
                    orderBuilder.setTattooId(resultSet.getLong(ColumnName.ORDERS_TATTOO_ID));
                    orderBuilder.setTattooName(resultSet.getString(ColumnName.ORDERS_TATTOO_NAME));
                    orderBuilder.setTattooPrice(resultSet.getBigDecimal(ColumnName.ORDERS_TATTOO_PRICE));
                    resultOrder = Optional.of(orderBuilder.build());
                }
                return resultOrder;
            }
        } catch (SQLException e) {
            logger.error("Error during searching order with id = " + id, e);
            throw new DaoException("Error during searching order with id = " + id, e);
        }
    }

    public List<Order> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                return createOrders(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for orders by user id.", e);
            throw new DaoException("Error during searching for orders by user id.", e);
        }
    }

    public List<Order> findByStatus(String status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_STATUS)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()) {
                return createOrders(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching orders with status: " + status, e);
            throw new DaoException("Error during searching orders with status: " + status, e);
        }
    }

    public Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ORDER_ID_PERSONAL)) {
            statement.setLong(1, orderId);
            statement.setString(2, userLogin);
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Order> resultOrder = Optional.empty();
                if (resultSet.next()) {
                    OrderBuilder orderBuilder = new OrderBuilder();
                    orderBuilder.setOrderId(resultSet.getLong(ColumnName.ORDERS_ID));
                    orderBuilder.setPaid(resultSet.getBigDecimal(ColumnName.ORDERS_PAID));
                    Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.ORDERS_REGISTRATION_DATE));
                    LocalDateTime localDateTime = LocalDateTime.of(
                            time.toLocalDateTime().toLocalDate(),
                            time.toLocalDateTime().toLocalTime());
                    orderBuilder.setRegistrationDate(localDateTime);
                    orderBuilder.setUserLogin(resultSet.getString(ColumnName.ORDERS_USER_LOGIN));
                    orderBuilder.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ColumnName.ORDERS_STATUS)));
                    orderBuilder.setTattooId(resultSet.getLong(ColumnName.ORDERS_TATTOO_ID));
                    orderBuilder.setTattooName(resultSet.getString(ColumnName.ORDERS_TATTOO_NAME));
                    orderBuilder.setTattooPrice(resultSet.getBigDecimal(ColumnName.ORDERS_TATTOO_PRICE));
                    resultOrder = Optional.of(orderBuilder.build());
                }
                return resultOrder;
            }
        } catch (SQLException e) {
            logger.error("Error during searching order with id = " + orderId
                    + " and user login = " + userLogin, e);
            throw new DaoException("Error during searching order with id = " + orderId
                    + " and user login = " + userLogin, e);
        }
    }

    public List<Order> findByStatusPersonal(String status, String userLogin) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_STATUS_PERSONAL)) {
            statement.setString(1, status);
            statement.setString(2, userLogin);
            try (ResultSet resultSet = statement.executeQuery()) {
                return createOrders(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching order with status = " + status
                    + " and user login = " + userLogin, e);
            throw new DaoException("Error during searching order with status = " + status
                    + " and user login = " + userLogin, e);
        }
    }

    public List<Order> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return createOrders(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for all orders.", e);
            throw new DaoException("Error during searching for all orders.", e);
        }
    }

    private List<Order> createOrders(ResultSet resultSet) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (resultSet.next()) {
            OrderBuilder orderBuilder = new OrderBuilder();
            orderBuilder.setOrderId(resultSet.getLong(ColumnName.ORDERS_ID));
            orderBuilder.setPaid(resultSet.getBigDecimal(ColumnName.ORDERS_PAID));
            Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.ORDERS_REGISTRATION_DATE));
            LocalDateTime localDateTime = LocalDateTime.of(
                    time.toLocalDateTime().toLocalDate(),
                    time.toLocalDateTime().toLocalTime());
            orderBuilder.setRegistrationDate(localDateTime);
            orderBuilder.setUserLogin(resultSet.getString(ColumnName.ORDERS_USER_LOGIN));
            orderBuilder.setOrderStatus(OrderStatus.valueOf(resultSet.getString(ColumnName.ORDERS_STATUS)));
            orderBuilder.setTattooId(resultSet.getLong(ColumnName.ORDERS_TATTOO_ID));
            orderBuilder.setTattooName(resultSet.getString(ColumnName.ORDERS_TATTOO_NAME));
            orderBuilder.setTattooPrice(resultSet.getBigDecimal(ColumnName.ORDERS_TATTOO_PRICE));
            orderList.add(orderBuilder.build());
        }
        return orderList;
    }
}
