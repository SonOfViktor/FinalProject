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
     * Register user.
     *
     * @param parameters the parameters for user registration
     * @return the boolean result of register new user
     * @throws ServiceException the service exception
     */
    boolean registerUser(Map<String, String> parameters) throws ServiceException;

    /**
     * Update email.
     *
     * @param parameters the map containing the new email to update
     * @param userId     the user id
     * @return the boolean result of updating of user email
     * @throws ServiceException the service exception
     */
    boolean updateEmail(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update password boolean.
     *
     * @param parameters the map containing the new password to update
     * @param userId     the user id
     * @return the boolean result of updating of user password
     * @throws ServiceException the service exception
     */
    boolean updatePassword(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update name boolean.
     *
     * @param parameters the map containing the new name to update
     * @param userId     the user id
     * @return the boolean result of updating of user name
     * @throws ServiceException the service exception
     */
    boolean updateName(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update surname.
     *
     * @param parameters the map containing the new surname to update
     * @param userId     the user id
     * @return the boolean result of updating of user surname
     * @throws ServiceException the service exception
     */
    boolean updateSurname(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update discount.
     *
     * @param parameters the map containing the additional discount to update
     * @param userId     the user id
     * @return the boolean result of updating of user discount
     * @throws ServiceException the service exception
     */
    boolean updateDiscount(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update balance.
     *
     * @param parameters the map containing the amount of money to update
     * @param userId     the user id
     * @return the boolean result of updating of user balance
     * @throws ServiceException the service exception
     */
    boolean updateBalance(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update user status.
     *
     * @param parameters the map containing the status to update
     * @param userId     the user id
     * @return the boolean result of updating of user status
     * @throws ServiceException the service exception
     */
    boolean updateStatus(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Update average rating.
     *
     * @param parameters the map containing the mark to update
     * @param userId     the user id
     * @return the boolean result of updating of average rating
     * @throws ServiceException the service exception
     */
    boolean updateAverageRating(Map<String, String> parameters, Long userId) throws ServiceException;

    /**
     * Find user by id.
     *
     * @param soughtId the sought id
     * @return the optional user
     * @throws ServiceException the service exception
     */
    Optional<User> findById(Long soughtId) throws ServiceException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional user
     * @throws ServiceException the service exception
     */
    Optional<User> findByLogin(String login) throws ServiceException;

    /**
     * Find user by login and password.
     *
     * @param login    the login
     * @param password the password
     * @return the optional user
     * @throws ServiceException the service exception
     */
    Optional<User> findByLoginAndPassword(String login, String password) throws ServiceException;

    /**
     * Find users by name.
     *
     * @param name the name
     * @return the list of users that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<User> findByName(String name) throws ServiceException;

    /**
     * Find users by surname.
     *
     * @param surname the surname
     * @return the list of users that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<User> findBySurname(String surname) throws ServiceException;

    /**
     * Find users by status.
     *
     * @param status the status
     * @return the list of users that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<User> findByStatus(String status) throws ServiceException;

    /**
     * Find all users.
     *
     * @return the list of all users
     * @throws ServiceException the service exception
     */
    List<User> findAll() throws ServiceException;
}
