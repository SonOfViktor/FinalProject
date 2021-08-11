package edy.epam.task6.model.service;

import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Tattoo;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TattooService {

    boolean AddNewTattoo(Map<String, String> parameters) throws ServiceException;

    Optional<Tattoo> findById(Long soughtId) throws ServiceException;

    List<Tattoo> findByNameAllActive(String name) throws ServiceException;

    List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max) throws ServiceException;

    List<Tattoo> findAll() throws ServiceException;

    List<Tattoo> findAllActive() throws ServiceException;

}
