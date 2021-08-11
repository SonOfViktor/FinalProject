package edy.epam.task6.model.service.impl;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.TattooDao;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.service.TattooService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TattooServiceImpl implements TattooService {

    public boolean AddNewTattoo(Map<String, String> parameters) throws ServiceException {
        boolean result = true;
        if(result) {
            TattooDao tattooDao = TattooDao.getInstance();
            try {
                result = tattooDao.add(parameters);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public Optional<Tattoo> findById(Long soughtId) throws ServiceException {
        TattooDao tattooDao = TattooDao.getInstance();
        try {
            Optional<Tattoo> tattoo;
            tattoo = tattooDao.findById(soughtId);
            return tattoo;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByNameAllActive(String name) throws ServiceException {
        TattooDao tattooDao = TattooDao.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByNameAllActive(name);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max)
            throws ServiceException {
        TattooDao tattooDao = TattooDao.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByPriceRangeAllActive(min, max);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findAll() throws ServiceException {
        TattooDao tattooDao = TattooDao.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findAll();
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findAllActive() throws ServiceException {
        TattooDao tattooDao = TattooDao.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findAllActive();
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
