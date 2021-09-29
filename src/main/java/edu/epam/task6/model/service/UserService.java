package edu.epam.task6.model.service;

import edu.epam.task6.model.entity.User;
import edu.epam.task6.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Register user boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean registerUser(Map<String, String> parameters) throws ServiceException;

    /**
     * Update email boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateEmail(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update password boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updatePassword(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update name boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateName(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update surname boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateSurname(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update discount boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateDiscount(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update balance boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateBalance(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update status boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateStatus(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update average rating boolean.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateAverageRating(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Find by id optional.
     *
     * @param soughtId the sought id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findById(Long soughtId) throws ServiceException;

    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findByLogin(String login) throws ServiceException;

    /**
     * Find by login and password optional.
     *
     * @param login    the login
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findByName(String name) throws ServiceException;

    /**
     * Find by surname list.
     *
     * @param surname the surname
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findBySurname(String surname) throws ServiceException;

    /**
     * Find by status list.
     *
     * @param status the status
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findByStatus(String status) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;
}
