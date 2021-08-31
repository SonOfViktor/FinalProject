package edy.epam.task6.model.service;

import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    boolean registerUser(Map<String, String> parameters) throws ServiceException;

    boolean updateEmail(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updatePassword(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updateName(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updateSurname(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updateDiscount(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updateBalance(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updateStatus(Map<String, String> parameters, Long userId) throws ServiceException;

    boolean updateRole(Map<String, String> parameters, Long userId) throws ServiceException;

    Optional<User> findById(Long soughtId) throws ServiceException;

    Optional<User> findByLogin(String login) throws ServiceException;

    Optional<User> findByLoginAndPassword(String login, String password) throws ServiceException;

    List<User> findByName(String name) throws ServiceException;

    List<User> findBySurname(String surname) throws ServiceException;

    List<User> findByStatus(String status) throws ServiceException;

    List<User> findAll() throws ServiceException;
}
