package edu.epam.task6.model.service;

import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TattooService {

    boolean AddNewTattoo(Map<String, String> parameters) throws ServiceException;

    boolean updateStatus(Map<String, String> parameters, Long tattooId) throws ServiceException;

    boolean updatePrice(Map<String, String> parameters, Long tattooId) throws ServiceException;

    boolean updateAverageRating(Map<String, String> parameters, Long tattooId) throws ServiceException;

    Optional<Tattoo> findById(Long soughtId) throws ServiceException;

    List<Tattoo> findByName(String name) throws ServiceException;

    List<Tattoo> findByPriceRange(String min, String max) throws ServiceException;

    List<Tattoo> findByPlace(String place) throws ServiceException;

    Optional<Tattoo> findByIdAllActive(Long soughtId) throws ServiceException;

    List<Tattoo> findByNameAllActive(String name) throws ServiceException;

    List<Tattoo> findByPlaceAllActive(String place) throws ServiceException;

    List<Tattoo> findByPriceRangeAllActive(String min, String max) throws ServiceException;

    List<Tattoo> findByStatus(String status) throws ServiceException;

    List<Tattoo> findAll() throws ServiceException;

    List<Tattoo> findAllActive() throws ServiceException;

}
