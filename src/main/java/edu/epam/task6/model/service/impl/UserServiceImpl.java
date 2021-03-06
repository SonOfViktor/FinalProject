package edu.epam.task6.model.service.impl;

import edu.epam.task6.model.dao.UserDao;
import edu.epam.task6.model.dao.impl.UserDaoImpl;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.util.HashGenerator;
import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.validator.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final Integer MAXIMUM_DISCOUNT_PERCENT = 50;
    private static final Integer MAXIMUM_BALANCE = 2000000000;
    private static final Validator validator = new Validator();
    private static UserServiceImpl instance;

    private UserServiceImpl(){}

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    public boolean registerUser(Map<String, String> parameters) throws ServiceException {
        boolean result = validator.validateUser(parameters);
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                String password = HashGenerator.hashPassword(parameters.get(ColumnName.USER_PASSWORD));
                parameters.computeIfPresent(ColumnName.USER_PASSWORD, (key, value) -> value = password);
                result = userDao.add(parameters);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateEmail(Map<String, String> parameters, Long userId) throws ServiceException {
        String email = parameters.get(RequestParameter.USER_EMAIL);
        boolean result = validator.validateEmail(email);
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                result = userDao.updateEmail(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updatePassword(Map<String, String> parameters, Long userId) throws ServiceException {
        String passwordNew = parameters.get(RequestParameter.USER_PASSWORD);
        boolean result = validator.validatePassword(passwordNew) ;
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                String password = HashGenerator.hashPassword(parameters.get(ColumnName.USER_PASSWORD));
                parameters.computeIfPresent(ColumnName.USER_PASSWORD, (key, value) -> value = password);
                result = userDao.updatePassword(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateName(Map<String, String> parameters, Long userId) throws ServiceException {
        String name = parameters.get(RequestParameter.USER_NAME);
        boolean result = validator.validateName(name);
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                result = userDao.updateName(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateSurname(Map<String, String> parameters, Long userId) throws ServiceException {
        String surname = parameters.get(RequestParameter.USER_SURNAME);
        boolean result = validator.validateName(surname);
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                result = userDao.updateSurname(parameters, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateDiscount(Map<String, String> parameters, Long userId) throws ServiceException {
        String discount = parameters.get(RequestParameter.USER_DISCOUNT);
        boolean result = validator.validateDiscount(discount) && validator.validateId(userId.toString());
        if(result) {
            try {
                UserDao userDao = UserDaoImpl.getInstance();
                Optional<User> user = userDao.findById(userId);
                if (user.isPresent()) {
                    int localDiscount = user.get().getDiscount() + Integer.parseInt(discount);
                    if (localDiscount > MAXIMUM_DISCOUNT_PERCENT) {
                        localDiscount = MAXIMUM_DISCOUNT_PERCENT;
                    }
                    String resultDiscount = String.valueOf(localDiscount);
                    parameters.computeIfPresent(ColumnName.USER_DISCOUNT, (key, value) -> value = resultDiscount);
                    result = userDao.updateDiscount(parameters, userId);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateBalance(Map<String, String> parameters, Long userId) throws ServiceException {
        String balance = parameters.get(RequestParameter.USER_BALANCE);
        boolean result = validator.validatePaid(balance);
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                BigDecimal localBalance = new BigDecimal(balance);
                Optional<User> localUser = userDao.findById(userId);
                if (localUser.isPresent()) {
                    if (localUser.get().getBalance().compareTo(BigDecimal.valueOf(MAXIMUM_BALANCE)) != 1) {
                        localBalance = localUser.get().getBalance().add(localBalance);
                        String finalBalance = localBalance.toString();
                        parameters.computeIfPresent(ColumnName.USER_BALANCE, (key, value) -> value = finalBalance);
                        result = userDao.updateBalance(parameters, userId);
                    } else {
                        result = false;
                    }
                } else {
                    result = false;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateStatus(Map<String, String> parameters, Long userId) throws ServiceException {
        boolean result;
        UserDao userDao = UserDaoImpl.getInstance();
        int statusId = switch (parameters.get(ColumnName.USER_STATUS)) {
            case "ACTIVE" -> 1;
            case "BLOCKED" -> 2;
            default -> 3;
        };
        try {
            result = userDao.updateStatus(statusId, userId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public boolean updateAverageRating(Map<String, String> parameters, Long userId) throws ServiceException {
        int grade = Integer.parseInt(parameters.get(ColumnName.USER_AVERAGE_RATING));
        boolean result = validator.validateAverageRating(String.valueOf(grade));
        if(result) {
            UserDao userDao = UserDaoImpl.getInstance();
            try {
                Optional<User> user = userDao.findById(userId);
                if (user.isPresent()) {
                    Double userGrade = user.get().getAverageRating();
                    Integer numberOfRatings = user.get().getNumberOfRatings();
                    if (numberOfRatings > 0) {
                        userGrade = userGrade * numberOfRatings + grade;
                        numberOfRatings += 1;
                        userGrade = userGrade / numberOfRatings;
                    } else {
                        userGrade = (double) grade;
                        numberOfRatings += 1;
                    }

                    result = userDao.updateAverageRating(userGrade, userId) &&
                            userDao.updateNumberOfRatings(numberOfRatings, userId);
                } else {
                    result = false;
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public Optional<User> findById(Long soughtId) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findById(soughtId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> findByLogin(String login) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findByLogin(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<User> findByLoginAndPassword(String login, String password) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        Optional<User> result = Optional.empty();
        try {
            Optional<User> user = userDao.findByLogin(login);
            if (user.isPresent()) {
                String hashPassword = HashGenerator.hashPassword(password);
                Optional<String> tablePassword = userDao.findPasswordByLogin(login);
                if (tablePassword.isPresent() && hashPassword.equals(tablePassword.get())) {
                    result = user;
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public List<User> findByName(String name) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> findBySurname(String surname) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> findByStatus(String status) throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> findAll() throws ServiceException {
        UserDao userDao = UserDaoImpl.getInstance();
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
