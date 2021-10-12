package edu.epam.task6.model.service;

import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Create order.
     *
     * @param parameters the parameters
     * @return the boolean result of order creation
     * @throws ServiceException the service exception
     */
    boolean createOrder(Map<String, String> parameters) throws ServiceException;

    /**
     * Update status boolean.
     *
     * @param parameters the map containing the status to change
     * @param orderId    the order id
     * @return the boolean status update result
     * @throws ServiceException the service exception
     */
    boolean updateStatus(Map<String, String> parameters, Long orderId) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the order id
     * @return the optional order
     * @throws ServiceException the service exception
     */
    Optional<Order> findById(Long id) throws ServiceException;

    /**
     * Find by user login list of orders.
     *
     * @param login the user login
     * @return the orders list belonging to the user with the received login
     * @throws ServiceException the service exception
     */
    List<Order> findByLogin(String login) throws ServiceException;

    /**
     * Find by status list.
     *
     * @param status the status of orders
     * @return the order list
     * @throws ServiceException the service exception
     */
    List<Order> findByStatus(String status) throws ServiceException;

    /**
     * Find by id personal optional.
     *
     * @param orderId   the order id
     * @param userLogin the user login
     * @return the optional order
     * @throws ServiceException the service exception
     */
    Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws ServiceException;

    /**
     * Find by status personal list.
     *
     * @param status    the status
     * @param userLogin the user login
     * @return the list of orders, with the desired status, owned by a user with a login
     * @throws ServiceException the service exception
     */
    List<Order> findByStatusPersonal(String status, String userLogin) throws ServiceException;

    /**
     * Find all orders list.
     *
     * @return the list of all orders
     * @throws ServiceException the service exception
     */
    List<Order> findAll() throws ServiceException;

}
