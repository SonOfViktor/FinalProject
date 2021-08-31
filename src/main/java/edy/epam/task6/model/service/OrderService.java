package edy.epam.task6.model.service;

import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderService {

    boolean createOrder(Map<String, String> parameters) throws ServiceException;

    boolean updateStatus(Map<String, String> parameters, Long orderId) throws ServiceException;

    Optional<Order> findById(Long id) throws ServiceException;

    List<Order> findByLogin(String login) throws ServiceException;

    List<Order> findByStatus(String status) throws ServiceException;

    Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws ServiceException;

    List<Order> findByStatusPersonal(String status, String userLogin) throws ServiceException;

    List<Order> findAll() throws ServiceException;

}
