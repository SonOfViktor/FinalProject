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
     * Create order boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createOrder(Map<String, String> parameters) throws ServiceException;

    /**
     * Update status boolean.
     *
     * @param parameters the parameters
     * @param orderId    the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStatus(Map<String, String> parameters, Long orderId) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findById(Long id) throws ServiceException;

    /**
     * Find by login list.
     *
     * @param login the login
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByLogin(String login) throws ServiceException;

    /**
     * Find by status list.
     *
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByStatus(String status) throws ServiceException;

    /**
     * Find by id personal optional.
     *
     * @param orderId   the order id
     * @param userLogin the user login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws ServiceException;

    /**
     * Find by status personal list.
     *
     * @param status    the status
     * @param userLogin the user login
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findByStatusPersonal(String status, String userLogin) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAll() throws ServiceException;

}
