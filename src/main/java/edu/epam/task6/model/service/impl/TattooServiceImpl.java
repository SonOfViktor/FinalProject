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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TattooServiceImpl implements TattooService {

    private static final Validator validator = new Validator();
    private static TattooServiceImpl instance;

    private TattooServiceImpl(){}

    public static TattooServiceImpl getInstance() {
        if (instance == null) {
            instance = new TattooServiceImpl();
        }
        return instance;
    }

    public boolean AddNewTattoo(Map<String, String> parameters) throws ServiceException {
        boolean result = validator.validateTattoo(parameters);
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

    public boolean updateStatus(Map<String, String> parameters, Long tattooId)
            throws ServiceException {
        boolean result;
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        int statusId = switch (parameters.get(ColumnName.TATTOOS_STATUS)) {
            case "ACTIVE" -> 1;
            case "OFFERED_BY_USER" -> 3;
            default -> 2;
        };
        try {
            result = tattooDao.updateStatus(statusId, tattooId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }

    public boolean updatePrice(Map<String, String> parameters, Long tattooId)
            throws ServiceException {
        String price = parameters.get(ColumnName.TATTOOS_PRICE);
        boolean result = validator.validatePrice(price);
        if(result) {
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            BigDecimal localPrice = BigDecimal.valueOf(Long.parseLong(price));
            try {
                result = tattooDao.updatePrice(localPrice, tattooId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean updateAverageRating(Map<String, String> parameters, Long tattooId) throws ServiceException {
        Integer grade = Integer.valueOf(parameters.get(ColumnName.TATTOOS_AVERAGE_RATING));
        boolean result = validator.validateAverageRating(grade.toString());
        if(result) {
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            try {
                Optional<Tattoo> tattoo = tattooDao.findById(tattooId);
                if (tattoo.isPresent()) {
                    Double tattooGrade = tattoo.get().getAverageRating();
                    Integer numberOfRatings = tattoo.get().getNumberOfRatings();
                    if (numberOfRatings > 0) {
                        tattooGrade = tattooGrade * numberOfRatings + grade;
                        numberOfRatings += 1;
                        tattooGrade = tattooGrade / numberOfRatings;
                    } else {
                        tattooGrade = Double.valueOf(grade);
                        numberOfRatings += 1;
                    }

                    result = tattooDao.updateAverageRating(tattooGrade, tattooId) &&
                            tattooDao.updateNumberOfRatings(numberOfRatings, tattooId);
                } else {
                    result = false;
                }
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

    public List<Tattoo> findByPriceRange(String min, String max)
            throws ServiceException {
        List<Tattoo> tattoosList = new ArrayList<>();
        boolean result = validator.validateMinMaxRange(min, max);
        if(result) {
            BigDecimal minRange = new BigDecimal(min);
            BigDecimal maxRange = new BigDecimal(max);
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            try {
                tattoosList = tattooDao.findByPriceRange(minRange, maxRange);
                return tattoosList;
            } catch (DaoException e){
                throw new ServiceException(e);
            }
        }
        return tattoosList;
    }

    public List<Tattoo> findByPlace(String place) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findByPlace(place);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Optional<Tattoo> findByIdAllActive(Long soughtId) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findByIdAllActive(soughtId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByNameAllActive(String name) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findByNameAllActive(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPlaceAllActive(String place) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findByPlaceAllActive(place);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findByPriceRangeAllActive(String min, String max)
            throws ServiceException {
        List<Tattoo> tattoosList = new ArrayList<>();
        boolean result = validator.validateMinMaxRange(min, max);
        if(result) {
            BigDecimal minRange = new BigDecimal(min);
            BigDecimal maxRange = new BigDecimal(max);
            TattooDao tattooDao = TattooDaoImpl.getInstance();
            try {
                tattoosList = tattooDao.findByPriceRangeAllActive(minRange, maxRange);
            } catch (DaoException e){
                throw new ServiceException(e);
            }
        }
        return tattoosList;
    }

    public List<Tattoo> findByStatus(String status) throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findByStatus(status);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findAll() throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findAll();
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }

    public List<Tattoo> findAllActive() throws ServiceException {
        TattooDao tattooDao = TattooDaoImpl.getInstance();
        try {
            return tattooDao.findAllActive();
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
