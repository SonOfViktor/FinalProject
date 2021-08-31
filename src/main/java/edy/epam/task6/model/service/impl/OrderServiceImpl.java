package edy.epam.task6.model.service.impl;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.ColumnName;
import edy.epam.task6.model.dao.OrderDao;
import edy.epam.task6.model.dao.impl.OrderDaoImpl;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.validator.Validator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    public boolean createOrder(Map<String, String> parameters) throws ServiceException {
        boolean result = Validator.validateOrder(parameters);
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
    //TODO Сделать валидацию
    public boolean updateStatus(Map<String, String> parameters, Long orderId) throws ServiceException {
        boolean result = true;
        if(result) {
            OrderDao orderDao = OrderDaoImpl.getInstance();
            int statusId = switch (parameters.get(ColumnName.ORDERS_STATUS)) {
                case "COMPLETED" -> 2;
                case "CANCELED" ->3;
                default -> 1;
            };
            System.out.println("orderStatus id = " + statusId);
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
