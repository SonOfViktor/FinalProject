package edy.epam.task6.model.dao;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.model.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderDao {

    boolean add(Map<String, String> parameters) throws DaoException;

    boolean updateStatus(int statusId, Long orderId) throws DaoException;

    Optional<Order> findById(Long id) throws DaoException;

    List<Order> findByLogin(String login) throws DaoException;

    List<Order> findByStatus(String status) throws DaoException;

    Optional<Order> findByIdPersonal(Long orderId, String userLogin) throws DaoException;

    List<Order> findByStatusPersonal(String status, String userLogin) throws DaoException;

    List<Order> findAll() throws DaoException;
}
