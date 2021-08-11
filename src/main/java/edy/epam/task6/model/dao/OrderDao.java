package edy.epam.task6.model.dao;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.model.builder.OrderBuilder;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.entity.OrderStatus;
import edy.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderDao {
    private static final Logger logger = LogManager.getLogger();

    private static OrderDao instance;

    @Language("SQL")
    //CREATE REGEX
    private static final String CREATE_ORDER = """
            INSERT INTO orders (paid, registration_date, users_user_id, 
            order_status_id, tattoos_tattoo_id)
            VALUES (?, ?, ?, ?, ?)""";
    //FIND REGEX
    private static final String FIND_BY_USER_ID = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id
            WHERE users.login=?""";
    private static final String FIND_ALL_ORDERS = """
            SELECT order_id, paid, orders.registration_date, users.login, order_statuses.status,
            tattoos_tattoo_id, tattoos.name, tattoos.price
            FROM orders
            JOIN users ON orders.users_user_id = users.user_id
            JOIN order_statuses ON orders.order_status_id = order_statuses.order_status_id
            JOIN tattoos ON orders.tattoos_tattoo_id = tattoos.tattoo_id""";
    //UPDATE REGEX
    private static final String UPDATE_STATUS = "UPDATE orders SET order_status_id=? WHERE order_id=?";

    private OrderDao(){}

    public static OrderDao getInstance(){
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_ORDER)) {
            statement.setBigDecimal(1, BigDecimal.valueOf(Long.parseLong(parameters.get(FieldName.ORDERS_PAID))));
            statement.setTimestamp(2, Timestamp.valueOf(parameters.get(FieldName.ORDERS_REGISTRATION_DATE)));
            statement.setLong(3, Long.parseLong(parameters.get(FieldName.ORDERS_USER_LOGIN)));
            statement.setLong(4, 1);
            statement.setLong(5, Long.parseLong(parameters.get(FieldName.ORDERS_TATTOO_ID)));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding tattoo.", e);
            throw new DaoException("Error during adding tattoo.", e);
        }
        return result;
    }

    public boolean updateStatus(int status_id, Long orderId) throws DaoException {
        boolean result = false;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)){
            statement.setInt(1, status_id);
            statement.setLong(2, orderId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating status of order with id = " + orderId, e);
            throw new DaoException("Error during updating status of order with id = " + orderId, e);
        }
        return result;
    }

    public List<Order> findByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_USER_ID)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()){
                return createOrders(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for orders by user id.", e);
            throw new DaoException("Error during searching for orders by user id.", e);
        }
    }

    public List<Order> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ORDERS)) {
            try (ResultSet resultSet = statement.executeQuery()){
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
            orderBuilder.setOrderId(resultSet.getLong(FieldName.ORDERS_ID));
            orderBuilder.setPaid(resultSet.getBigDecimal(FieldName.ORDERS_PAID));
            orderBuilder.setRegistrationDate(resultSet.getTimestamp(FieldName.ORDERS_REGISTRATION_DATE));
            orderBuilder.setUserLogin(resultSet.getString(FieldName.ORDERS_USER_LOGIN));
            orderBuilder.setOrderStatus(OrderStatus.valueOf(resultSet.getString(FieldName.ORDERS_STATUS)));
            orderBuilder.setTattooId(resultSet.getLong(FieldName.ORDERS_TATTOO_ID));
            orderBuilder.setTattooName(resultSet.getString(FieldName.ORDERS_TATTOO_NAME));
            orderBuilder.setTattooPrice(resultSet.getBigDecimal(FieldName.ORDERS_TATTOO_PRICE));
            orderList.add(orderBuilder.build());
        }
        return orderList;
    }
}
