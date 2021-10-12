package edu.epam.task6.model.service;

import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The interface Tattoo service.
 */
public interface TattooService {

    /**
     * Add new tattoo.
     *
     * @param parameters the map with parameters for creating a tattoo
     * @return the boolean result of creation
     * @throws ServiceException the service exception
     */
    boolean AddNewTattoo(Map<String, String> parameters) throws ServiceException;

    /**
     * Update tattoo status.
     *
     * @param parameters the map with status parameters
     * @param tattooId   the tattoo id
     * @return the boolean result of update
     * @throws ServiceException the service exception
     */
    boolean updateStatus(Map<String, String> parameters, Long tattooId) throws ServiceException;

    /**
     * Update price.
     *
     * @param parameters the map with price parameters
     * @param tattooId   the tattoo id
     * @return the boolean result of update
     * @throws ServiceException the service exception
     */
    boolean updatePrice(Map<String, String> parameters, Long tattooId) throws ServiceException;

    /**
     * Update average rating of tattoo.
     *
     * @param parameters the map with mark parameter
     * @param tattooId   the tattoo id
     * @return the boolean result of update
     * @throws ServiceException the service exception
     */
    boolean updateAverageRating(Map<String, String> parameters, Long tattooId) throws ServiceException;

    /**
     * Find by id.
     *
     * @param soughtId the sought id
     * @return the optional tattoo
     * @throws ServiceException the service exception
     */
    Optional<Tattoo> findById(Long soughtId) throws ServiceException;

    /**
     * Find by tattoo name.
     *
     * @param name the tattoo name
     * @return the list of tattoos with the desired name
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByName(String name) throws ServiceException;

    /**
     * Find tattoos by price range.
     *
     * @param min the min price
     * @param max the max price
     * @return the list of tattoos in the desired price range
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByPriceRange(String min, String max) throws ServiceException;

    /**
     * Find tattoos by place.
     *
     * @param place the place
     * @return the list of tattoos in this place
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByPlace(String place) throws ServiceException;

    /**
     * Find tattoo by id among tattoos with an active status.
     *
     * @param soughtId the sought id
     * @return the optional tattoo
     * @throws ServiceException the service exception
     */
    Optional<Tattoo> findByIdAllActive(Long soughtId) throws ServiceException;

    /**
     * Find tattoos by name among tattoos with an active status.
     *
     * @param name the name
     * @return the list of tattoos that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByNameAllActive(String name) throws ServiceException;

    /**
     * Find tattoos by place among tattoos with an active status.
     *
     * @param place the place
     * @return the list of tattoos that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByPlaceAllActive(String place) throws ServiceException;

    /**
     * Find tattoos by price range among tattoos with an active status.
     *
     * @param min the min price
     * @param max the max price
     * @return the list of tattoos that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByPriceRangeAllActive(String min, String max) throws ServiceException;

    /**
     * Find tattoos by status.
     *
     * @param status the status
     * @return the list of tattoos that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<Tattoo> findByStatus(String status) throws ServiceException;

    /**
     * Find all tattoos.
     *
     * @return the list of all tattoos
     * @throws ServiceException the service exception
     */
    List<Tattoo> findAll() throws ServiceException;

    /**
     * Find all tattoos among tattoos with an active status.
     *
     * @return the list of tattoos that satisfy the condition
     * @throws ServiceException the service exception
     */
    List<Tattoo> findAllActive() throws ServiceException;

}
