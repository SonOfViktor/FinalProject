package edu.epam.task6.model.dao;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {

    /**
     * Add new user.
     *
     * @param parameters the map of parameters to add to the database
     * @return the boolean result of adding a user to the database
     * @throws DaoException the dao exception
     */
    boolean add(Map<String, String> parameters) throws DaoException;

    /**
     * Update email for user with selected id.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean result of updating user email in the database
     * @throws DaoException the dao exception
     */
    boolean updateEmail(Map<String, String> parameters, Long userId) throws DaoException;

    /**
     * Update password for user with selected id.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean result of updating user password in the database
     * @throws DaoException the dao exception
     */
    boolean updatePassword(Map<String, String> parameters, Long userId) throws DaoException;

    /**
     * Update name for user with selected id.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean result of updating user name in the database
     * @throws DaoException the dao exception
     */
    boolean updateName(Map<String, String> parameters, Long userId) throws DaoException;

    /**
     * Update surname for user with selected id.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean result of updating user surname in the database
     * @throws DaoException the dao exception
     */
    boolean updateSurname(Map<String, String> parameters, Long userId) throws DaoException;

    /**
     * Update discount for user with selected id.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean result of updating user discount in the database
     * @throws DaoException the dao exception
     */
    boolean updateDiscount(Map<String, String> parameters, Long userId) throws DaoException;

    /**
     * Update balance for user with selected id.
     *
     * @param parameters the parameters
     * @param userId     the user id
     * @return the boolean result of updating user balance in the database
     * @throws DaoException the dao exception
     */
    boolean updateBalance(Map<String, String> parameters, Long userId) throws DaoException;

    /**
     * Update status for user with selected id.
     *
     * @param statusId the status id
     * @param userId   the user id
     * @return the boolean result of updating user status in the database
     * @throws DaoException the dao exception
     */
    boolean updateStatus(int statusId, Long userId) throws DaoException;

    /**
     * Update average rating for user with selected id.
     *
     * @param grade  the grade
     * @param userId the user id
     * @return the boolean result of updating user average rating in the database
     * @throws DaoException the dao exception
     */
    boolean updateAverageRating(double grade, Long userId) throws DaoException;

    /**
     * Update number of ratings for user with selected id.
     *
     * @param numberOfRatings the number of ratings
     * @param userId          the user id
     * @return the boolean result of updating user number of ratings in the database
     * @throws DaoException the dao exception
     */
    boolean updateNumberOfRatings(int numberOfRatings, Long userId) throws DaoException;

    /**
     * Find user by id.
     *
     * @param soughtId the sought id
     * @return the optional user
     * @throws DaoException the dao exception
     */
    Optional<User> findById(Long soughtId) throws DaoException;

    /**
     * Find user by login.
     *
     * @param login the login
     * @return the optional user
     * @throws DaoException the dao exception
     */
    Optional<User> findByLogin(String login) throws DaoException;

    /**
     * Find password by login.
     *
     * @param login the login
     * @return the optional user
     * @throws DaoException the dao exception
     */
    Optional<String> findPasswordByLogin(String login) throws DaoException;

    /**
     * Find a list of all users with the searched name.
     *
     * @param name the name
     * @return the list of all found users in the database which have the search name
     * @throws DaoException the dao exception
     */
    List<User> findByName(String name) throws DaoException;

    /**
     * Find a list of all users with the searched surname.
     *
     * @param surname the surname
     * @return the list of all found users in the database which have the search surname
     * @throws DaoException the dao exception
     */
    List<User> findBySurname(String surname) throws DaoException;

    /**
     * Find a list of all users with the searched status.
     *
     * @param status the status
     * @return the list of all found users in the database which have the search status
     * @throws DaoException the dao exception
     */
    List<User> findByStatus(String status) throws DaoException;

    /**
     * Find all users.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;
}
