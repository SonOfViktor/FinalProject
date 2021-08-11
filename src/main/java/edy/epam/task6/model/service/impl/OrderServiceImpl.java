package edy.epam.task6.model.service.impl;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.OrderDao;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.dao.MapKeys;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    public boolean createOrder(Map<String, String> parameters) throws ServiceException {
        boolean result = true;
        if(result) {
            OrderDao orderDao = OrderDao.getInstance();
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
            OrderDao orderDao = OrderDao.getInstance();
            int status_id = switch (parameters.get(MapKeys.USER_STATUS)) {
                case "COMPLETED" -> 2;
                case "BLOCKED" ->3;
                default -> 1;
            };
            try {
                result = orderDao.updateStatus(status_id, orderId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public List<Order> findByLogin(String login) throws ServiceException {
        OrderDao orderDao = OrderDao.getInstance();
        try {
            List<Order> orderList = orderDao.findByLogin(login);
            return orderList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Order> findAll() throws ServiceException {
        OrderDao orderDao = OrderDao.getInstance();
        try {
            List<Order> orderList = orderDao.findAll();
            return orderList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
