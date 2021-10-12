package edu.epam.task6.model.dao;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Add order.
     *
     * @param parameters the map of parameters to add to the database
     * @return the boolean result of adding an order to the database
     * @throws DaoException the dao exception
     */
    boolean add(Map<String, String> parameters) throws DaoException;

    /**
     * Update status.
     *
     * @param statusId the status id
     * @param orderId  the order id
     * @return the boolean result of updating an order status in the database
     * @throws DaoException the dao exception
     */
    boolean updateStatus(int statusId, Long orderId) throws DaoException;

    /**
     * Find user by id.
     *
     * @param id the id
     * @return the optional user
     * @throws DaoException the dao exception
     */
    Optional<Order> findById(Long id) throws DaoException;

    /**
     * Find a list of all orders belonging to a user with a login.
     *
     * @param login the login
     * @return the list of all found orders in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Order> findByLogin(String login) throws DaoException;

    /**
     * Find all orders with the required status.
     *
     * @param status the status
     * @return the list of all found orders in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Order> findByStatus(String status) throws DaoException;

    /**
     * Find an order among the orders of a user with a login.
     *
     * @param orderId   the order id
     * @param userLogin the user login
     * @return the optional order that match the search parameters
     * @throws DaoException the dao exception
     */
    Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws DaoException;

    /**
     * Find all orders with the desired status for the user with the login.
     *
     * @param status    the status
     * @param userLogin the user login
     * @return the list of all found orders in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Order> findByStatusPersonal(String status, String userLogin) throws DaoException;

    /**
     * Find all orders.
     *
     * @return the list of all orders in the database
     * @throws DaoException the dao exception
     */
    List<Order> findAll() throws DaoException;
}
