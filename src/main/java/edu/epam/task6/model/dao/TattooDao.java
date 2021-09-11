package edu.epam.task6.model.dao;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.entity.Tattoo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TattooDao {

    boolean add(Map<String, String> parameters) throws DaoException;

    boolean updateStatus(int statusId, Long tattooId) throws DaoException;

    boolean updatePrice(BigDecimal price, Long tattooId) throws DaoException;

    boolean updateAverageRating(double grade, Long userId) throws DaoException;

    Optional<Tattoo> findById(Long soughtId) throws DaoException;

    List<Tattoo> findByName(String name) throws DaoException;

    List<Tattoo> findByPriceRange(BigDecimal min, BigDecimal max) throws DaoException;

    List<Tattoo> findByPlace(String place) throws DaoException;

    Optional<Tattoo> findByIdAllActive(Long soughtId) throws DaoException;

    List<Tattoo> findByNameAllActive(String name) throws DaoException;

    List<Tattoo> findByPlaceAllActive(String place) throws DaoException;

    List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max) throws DaoException;

    List<Tattoo> findByStatus(String status) throws DaoException;

    List<Tattoo> findAll() throws DaoException;

    List<Tattoo> findAllActive() throws DaoException;

    List<Tattoo> findNumberActive(int number) throws DaoException;
}
