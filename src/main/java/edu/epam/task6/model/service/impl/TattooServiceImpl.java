package edu.epam.task6.model.service.impl;

import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.TattooDao;
import edu.epam.task6.model.dao.impl.TattooDaoImpl;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.validator.Validator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TattooServiceImpl implements TattooService {

    public boolean AddNewTattoo(Map<String, String> parameters) throws ServiceException {
        boolean result = true;
        if(result) {
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            try {
                result = tattooDao.add(parameters);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }
    //TODO Сделать валидацию
    public boolean updateStatus(Map<String, String> parameters, Long tattooId)
            throws ServiceException {
        boolean result = true;
        if(result) {
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            int statusId = switch (parameters.get(ColumnName.TATTOOS_STATUS)) {
                case "LOCKED" -> 2;
                case "OFFERED_BY_USER" -> 3;
                default -> 1;
            };
            try {
                result = tattooDao.updateStatus(statusId, tattooId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updatePrice(Map<String, String> parameters, Long tattooId)
            throws ServiceException {
        boolean result = true;
        if(result) {
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            BigDecimal price = BigDecimal.valueOf(Long.valueOf(parameters.get(ColumnName.TATTOOS_PRICE)));
            try {
                result = tattooDao.updatePrice(price, tattooId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateAverageRating(Map<String, String> parameters, Long userId) throws ServiceException {
        String grade = parameters.get(ColumnName.TATTOOS_AVERAGE_RATING);
        boolean result = Validator.validateAverageRating(grade);
        if(result) {
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            try {
                result = tattooDao.updateAverageRating(Double.valueOf(grade), userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public Optional<Tattoo> findById(Long soughtId) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            Optional<Tattoo> tattoo;
            tattoo = tattooDao.findById(soughtId);
            return tattoo;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByName(String name) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByName(name);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPriceRange(BigDecimal min, BigDecimal max)
            throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByPriceRange(min, max);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPlace(String place) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByPlace(place);
            return tattoosList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Tattoo> findByIdAllActive(Long soughtId) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            Optional<Tattoo> tattoo;
            tattoo = tattooDao.findByIdAllActive(soughtId);
            return tattoo;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByNameAllActive(String name) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByNameAllActive(name);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPlaceAllActive(String place) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByPlaceAllActive(place);
            return tattoosList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max)
            throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByPriceRangeAllActive(min, max);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByStatus(String status) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findByStatus(status);
            return tattoosList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findAll() throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findAll();
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findAllActive() throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findAllActive();
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findNumberActive(int number) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            List<Tattoo> tattoosList;
            tattoosList = tattooDao.findNumberActive(number);
            return tattoosList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
