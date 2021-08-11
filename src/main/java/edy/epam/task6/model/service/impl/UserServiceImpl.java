package edy.epam.task6.model.service.impl;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.UserDao;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.util.HashGenerator;
import edy.epam.task6.model.dao.MapKeys;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    public boolean registerUser(Map<String, String> parameters) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                String password = HashGenerator.generatePassword(parameters.get(MapKeys.USER_PASSWORD));
                parameters.computeIfPresent(MapKeys.USER_PASSWORD, (key, value) -> value = password);
                result = userDao.add(parameters);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateEmail(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                result = userDao.updateEmail(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updatePassword(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                result = userDao.updatePassword(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateName(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                result = userDao.updateName(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateSurname(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                result = userDao.updateSurname(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateDiscount(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                result = userDao.updateDiscount(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateBalance(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            try {
                result = userDao.updateBalance(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateStatus(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result = true;
        if(result) {
            UserDao userDao = UserDao.getInstance();
            int status_id = switch (parameters.get(MapKeys.USER_STATUS)) {
                case "ACTIVE" -> 1;
                case "BLOCKED" -> 2;
                default -> 1;
            };
            try {
                result = userDao.updateStatus(status_id, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public Optional<User> findById(Long soughtId) throws ServiceException {
        UserDao userDao = UserDao.getInstance();
        try {
            Optional<User> user = userDao.findById(soughtId);
            return user;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public Optional<User> findByLogin(String login) throws ServiceException {
        UserDao userDao = UserDao.getInstance();
        try {
            Optional<User> user = userDao.findByLogin(login);
            return user;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<User> findByName(String name) throws ServiceException {
        UserDao userDao = UserDao.getInstance();
        try {
            List<User> userList = userDao.findByName(name);
            return userList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<User> findBySurname(String surname) throws ServiceException {
        UserDao userDao = UserDao.getInstance();
        try {
            List<User> userList = userDao.findBySurname(surname);
            return userList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<User> findAll() throws ServiceException {
        UserDao userDao = UserDao.getInstance();
        try {
            List<User> userList = userDao.findAll();
            return userList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<User> findAllBlocked() throws ServiceException {
        UserDao userDao = UserDao.getInstance();
        try {
            List<User> userList = userDao.findAllBlocked();
            return userList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
