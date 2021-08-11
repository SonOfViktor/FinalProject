package edy.epam.task6.model.service;

import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {

    boolean createOrder(Map<String, String> parameters) throws ServiceException;

    boolean updateStatus(Map<String, String> parameters, Long orderId) throws ServiceException;

    List<Order> findByLogin(String login) throws ServiceException;

    List<Order> findAll() throws ServiceException;

}
