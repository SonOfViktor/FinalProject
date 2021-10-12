package edu.epam.task6.model.dao;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.entity.Tattoo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Tattoo dao.
 */
public interface TattooDao {

    /**
     * Add new tattoo.
     *
     * @param parameters the map of parameters to add to the database
     * @return the boolean result of adding a tattoo to the database
     * @throws DaoException the dao exception
     */
    boolean add(Map<String, String> parameters) throws DaoException;

    /**
     * Update status for tattoo with selected id.
     *
     * @param statusId the status id
     * @param tattooId the tattoo id
     * @return the boolean result of updating tattoo status in the database
     * @throws DaoException the dao exception
     */
    boolean updateStatus(int statusId, Long tattooId) throws DaoException;

    /**
     * Update price for tattoo with selected id.
     *
     * @param price    the price
     * @param tattooId the tattoo id
     * @return the boolean result of updating tattoo price in the database
     * @throws DaoException the dao exception
     */
    boolean updatePrice(BigDecimal price, Long tattooId) throws DaoException;

    /**
     * Update average rating for tattoo with selected id.
     *
     * @param grade  the grade
     * @param tattooId the tattoo id
     * @return the boolean result of updating tattoo average rating in the database
     * @throws DaoException the dao exception
     */
    boolean updateAverageRating(double grade, Long tattooId) throws DaoException;

    /**
     * Update number of ratings for tattoo with selected id.
     *
     * @param numberOfRatings the number of ratings
     * @param tattooId        the tattoo id
     * @return the boolean result of updating tattoo number of ratings in the database
     * @throws DaoException the dao exception
     */
    boolean updateNumberOfRatings(int numberOfRatings, Long tattooId) throws DaoException;

    /**
     * Find tattoo by id.
     *
     * @param soughtId the sought id
     * @return the optional tattoo
     * @throws DaoException the dao exception
     */
    Optional<Tattoo> findById(Long soughtId) throws DaoException;

    /**
     * Find list of tattoos by name.
     *
     * @param name the name
     * @return the list of all found tattoos in the database which have the search name
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByName(String name) throws DaoException;

    /**
     * Find list of tattoos by price range.
     *
     * @param min the min price
     * @param max the max price
     * @return the list of all found tattoos in the database which are in the desired price range
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByPriceRange(BigDecimal min, BigDecimal max) throws DaoException;

    /**
     * Find list of tattoos by place.
     *
     * @param place the place
     * @return the list of all found tattoos in the database which are in the desired place
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByPlace(String place) throws DaoException;

    /**
     * Find tattoo by id among tattoos with an active status.
     *
     * @param soughtId the sought id
     * @return the optional tattoo
     * @throws DaoException the dao exception
     */
    Optional<Tattoo> findByIdAllActive(Long soughtId) throws DaoException;

    /**
     * Find tattoos by name among tattoos with an active status.
     *
     * @param name the name
     * @return the list of all found tattoos in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByNameAllActive(String name) throws DaoException;

    /**
     * Find tattoos by place among tattoos with an active status.
     *
     * @param place the place
     * @return the list of all found tattoos in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByPlaceAllActive(String place) throws DaoException;

    /**
     * Find tattoos by price range among tattoos with an active status.
     *
     * @param min the min price
     * @param max the max price
     * @return the list of all found tattoos in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max) throws DaoException;

    /**
     * Find tattoos by status.
     *
     * @param status the status
     * @return the list of all found tattoos in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Tattoo> findByStatus(String status) throws DaoException;

    /**
     * Find all tattoos.
     *
     * @return the list of all tattoos in the database
     * @throws DaoException the dao exception
     */
    List<Tattoo> findAll() throws DaoException;

    /**
     * Find all tattoos among tattoos with an active status.
     *
     * @return the list of all found tattoos in the database that match the search parameters
     * @throws DaoException the dao exception
     */
    List<Tattoo> findAllActive() throws DaoException;
}
