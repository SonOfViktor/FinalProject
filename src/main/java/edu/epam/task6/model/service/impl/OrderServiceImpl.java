package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.OrderDao;
import edu.epam.task6.model.dao.impl.OrderDaoImpl;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.validator.Validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private static final Validator validator = new Validator();
    private static OrderServiceImpl instance;

    private OrderServiceImpl(){}

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    public boolean createOrder(Map<String, String> parameters) throws ServiceException {
        boolean result = validator.validateOrder(parameters);
        if(result) {
            OrderDao orderDao = OrderDaoImpl.getInstance();
            try {
                result = orderDao.add(parameters);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateStatus(Map<String, String> parameters, Long orderId) throws ServiceException {
        boolean result = true;
        if(result) {
            OrderDao orderDao = OrderDaoImpl.getInstance();
            int statusId = switch (parameters.get(ColumnName.ORDERS_STATUS)) {
                case "ACTIVE" -> 1;
                case "COMPLETED" -> 2;
                case "COMPLETED_AND_ASSESSED" -> 4;
                default -> 3;
            };
            try {
                result = orderDao.updateStatus(statusId, orderId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public Optional<Order> findById(Long id) throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            Optional<Order> order = orderDao.findById(id);
            return order;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Order> findByLogin(String login) throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            List<Order> orderList = orderDao.findByLogin(login);
            return orderList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> findByStatus(String status) throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            List<Order> orderList = orderDao.findByStatus(status);
            return orderList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            Optional<Order> order = orderDao.findByIdPersonal(orderId, userLogin);
            return order;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Order> findByStatusPersonal(String status, String userLogin) throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            List<Order> orderList = orderDao.findByStatusPersonal(status, userLogin);
            return orderList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Order> findAll() throws ServiceException {
        OrderDao orderDao = OrderDaoImpl.getInstance();
        try {
            List<Order> orderList = orderDao.findAll();
            return orderList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
