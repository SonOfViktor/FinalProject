package edy.epam.task6.model.dao;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserDao {

    boolean add(Map<String, String> parameters) throws DaoException;

    boolean updateEmail(Map<String, String> parameters, Long userId) throws DaoException;

    boolean updatePassword(Map<String, String> parameters, Long userId) throws DaoException;

    boolean updateName(Map<String, String> parameters, Long userId) throws DaoException;

    boolean updateSurname(Map<String, String> parameters, Long userId) throws DaoException;

    boolean updateDiscount(Map<String, String> parameters, Long userId) throws DaoException;

    boolean updateBalance(Map<String, String> parameters, Long userId) throws DaoException;

    boolean updateStatus(int statusId, Long userId) throws DaoException;

    boolean updateRole(int statusId, Long userId) throws DaoException;

    Optional<User> findById(Long soughtId) throws DaoException;

    Optional<User> findByLogin(String login) throws DaoException;

    Optional<String> findPasswordByLogin(String login) throws DaoException;

    List<User> findByName(String name) throws DaoException;

    List<User> findBySurname(String surname) throws DaoException;

    List<User> findByStatus(String status) throws DaoException;

    List<User> findAll() throws DaoException;
}
