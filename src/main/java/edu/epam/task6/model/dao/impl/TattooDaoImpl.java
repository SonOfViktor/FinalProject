package edu.epam.task6.model.dao.impl;

import edu.epam.task6.model.dao.TattooDao;
import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.builder.TattooBuilder;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.BodyPart;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;
import edu.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class TattooDaoImpl implements TattooDao {
    private static final Logger logger = LogManager.getLogger();

    private static TattooDaoImpl instance;

    @Language("SQL")
    //CREATE REGEX
    private static final String CREATE_TATTOO = """
            INSERT INTO tattoos (name, description, price, width, height, 
            image_url, tattoo_status_id, body_part_id, users_user_id)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)""";
    //FIND REGEX
    private static final String FIND_BY_ID = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE tattoo_id=?""";

    private static final String FIND_BY_NAME_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE name=?""";
    private static final String FIND_BY_PRICE_RANGE_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE price BETWEEN ? AND ?""";
    private static final String FIND_BY_PLACE_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE body_parts.place=?""";
    private static final String FIND_BY_ID_AND_STATUS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE tattoo_id=? AND tattoo_statuses.tattoo_status=?""";
    private static final String FIND_BY_NAME_AND_STATUS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE name=? AND tattoo_statuses.tattoo_status=?""";
    private static final String FIND_BY_PLACE_AND_STATUS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE body_parts.place=? AND tattoo_statuses.tattoo_status=?""";
    private static final String FIND_BY_PRICE_RANGE_AND_STATUS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE price BETWEEN ? AND ? AND tattoo_statuses.tattoo_status=?""";
    private static final String FIND_BY_STATUS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE tattoo_statuses.tattoo_status=?""";
    private static final String FIND_ALL_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            ORDER BY tattoo_id ASC""";
    private static final String FIND_ALL_ACTIVE_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE tattoo_statuses.tattoo_status=?""";
    //UPDATE REGEX
    private static final String UPDATE_STATUS = "UPDATE tattoos SET tattoos.tattoo_status_id=? WHERE tattoo_id=?";
    private static final String UPDATE_PRICE = "UPDATE tattoos SET tattoos.price=? WHERE tattoo_id=?";

    private TattooDaoImpl(){}

    public static TattooDaoImpl getInstance(){
        if (instance == null) {
            instance = new TattooDaoImpl();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TATTOO)) {
            statement.setString(1, parameters.get(ColumnName.TATTOOS_NAME));
            statement.setString(2, parameters.get(ColumnName.TATTOOS_DESCRIPTION));
            statement.setBigDecimal(3, BigDecimal.valueOf(Long.parseLong(parameters.get(ColumnName.TATTOOS_PRICE))));
            statement.setInt(4, Integer.valueOf(parameters.get(ColumnName.TATTOOS_WIDTH)));
            statement.setInt(5, Integer.valueOf(parameters.get(ColumnName.TATTOOS_HEIGHT)));
            statement.setString(6, parameters.get(ColumnName.TATTOOS_IMAGE_URL));
            statement.setInt(7, Integer.valueOf(parameters.get(ColumnName.TATTOOS_STATUS)));
            statement.setInt(8, Integer.valueOf(parameters.get(ColumnName.TATTOOS_PLACE)));
            statement.setInt(9, Integer.valueOf(parameters.get(ColumnName.TATTOOS_USER_ID)));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding tattoo.", e);
            throw new DaoException("Error during adding tattoo.", e);
        }
        return result;
    }

    public boolean updateStatus(int statusId, Long tattooId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS)){
            statement.setInt(1, statusId);
            statement.setLong(2, tattooId);
            result = statement.executeUpdate()>0;
        } catch (SQLException e){
            logger.error("Error during updating status of tattoo with id = " + tattooId, e);
            throw new DaoException("Error during updating status of tattoo with id = " + tattooId, e);
        }
        return result;
    }

    public boolean updatePrice(BigDecimal price, Long tattooId) throws DaoException {
        boolean result;
        try(Connection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_PRICE)){
            statement.setBigDecimal(1, price);
            statement.setLong(2, tattooId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e){
            logger.error("Error during updating price of tattoo with id = " + tattooId, e);
            throw new DaoException("Error during updating price of tattoo with id = " + tattooId, e);
        }
        return result;
    }

    public Optional<Tattoo> findById(Long soughtId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, soughtId.intValue());
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Tattoo> resultTattoo = Optional.empty();
                if (resultSet.next()) {
                    TattooBuilder tattooBuilder = new TattooBuilder();
                    tattooBuilder.setCatalogId(resultSet.getLong(ColumnName.TATTOOS_ID));
                    tattooBuilder.setName(resultSet.getString(ColumnName.TATTOOS_NAME));
                    tattooBuilder.setDescription(resultSet.getString(ColumnName.TATTOOS_DESCRIPTION));
                    tattooBuilder.setPrice(resultSet.getBigDecimal(ColumnName.TATTOOS_PRICE));
                    tattooBuilder.setWidth(resultSet.getInt(ColumnName.TATTOOS_WIDTH));
                    tattooBuilder.setHeight(resultSet.getInt(ColumnName.TATTOOS_HEIGHT));
                    tattooBuilder.setImageUrl(resultSet.getString(ColumnName.TATTOOS_IMAGE_URL));
                    tattooBuilder.setStatus(TattooStatus.valueOf(resultSet.getString(ColumnName.TATTOOS_STATUS)));
                    tattooBuilder.setPlaces(BodyPart.valueOf(resultSet.getString(ColumnName.TATTOOS_PLACE)));
                    tattooBuilder.setUserId(resultSet.getLong(ColumnName.TATTOOS_USER_ID));
                    resultTattoo = Optional.of(tattooBuilder.build());
                }
                return resultTattoo;
            }
        } catch (SQLException e) {
            logger.error("Error during searching tattoo by ID in catalog.", e);
            throw new DaoException("Error during searching tattoo by ID in catalog.", e);
        }
    }

    public List<Tattoo> findByName(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_TATTOOS)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching tattoos with such name in tattoos catalog.", e);
            throw new DaoException("Error during searching tattoos with such name in tattoos catalog.", e);
        }
    }

    public List<Tattoo> findByPriceRange(BigDecimal min, BigDecimal max) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PRICE_RANGE_TATTOOS)) {
            statement.setBigDecimal(1, min);
            statement.setBigDecimal(2, max);
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching tattoos in a given price range in tattoos catalog.", e);
            throw new DaoException("Error during searching tattoos in a given price range in tattoos catalog.", e);
        }
    }

    public List<Tattoo> findByPlace(String place) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PLACE_TATTOOS)) {
            statement.setString(1, place);
            try (ResultSet resultSet = statement.executeQuery()) {
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching tattoos with such place in tattoos catalog.", e);
            throw new DaoException("Error during searching tattoos with such place in tattoos catalog.", e);
        }
    }

    public Optional<Tattoo> findByIdAllActive(Long soughtId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_AND_STATUS)) {
            statement.setInt(1, soughtId.intValue());
            statement.setString(2, TattooStatus.ACTIVE.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                Optional<Tattoo> resultTattoo = Optional.empty();
                if (resultSet.next()) {
                    TattooBuilder tattooBuilder = new TattooBuilder();
                    tattooBuilder.setCatalogId(resultSet.getLong(ColumnName.TATTOOS_ID));
                    tattooBuilder.setName(resultSet.getString(ColumnName.TATTOOS_NAME));
                    tattooBuilder.setDescription(resultSet.getString(ColumnName.TATTOOS_DESCRIPTION));
                    tattooBuilder.setPrice(resultSet.getBigDecimal(ColumnName.TATTOOS_PRICE));
                    tattooBuilder.setWidth(resultSet.getInt(ColumnName.TATTOOS_WIDTH));
                    tattooBuilder.setHeight(resultSet.getInt(ColumnName.TATTOOS_HEIGHT));
                    tattooBuilder.setImageUrl(resultSet.getString(ColumnName.TATTOOS_IMAGE_URL));
                    tattooBuilder.setStatus(TattooStatus.valueOf(resultSet.getString(ColumnName.TATTOOS_STATUS)));
                    tattooBuilder.setPlaces(BodyPart.valueOf(resultSet.getString(ColumnName.TATTOOS_PLACE)));
                    tattooBuilder.setUserId(resultSet.getLong(ColumnName.TATTOOS_USER_ID));
                    resultTattoo = Optional.of(tattooBuilder.build());
                }
                return resultTattoo;
            }
        } catch (SQLException e) {
            logger.error("Error during searching ACTIVE tattoo with such ID in catalog.", e);
            throw new DaoException("Error during searching ACTIVE tattoo with such ID in catalog.", e);
        }
    }

    public List<Tattoo> findByNameAllActive(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_AND_STATUS)) {
            statement.setString(1, name);
            statement.setString(2, TattooStatus.ACTIVE.name());
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching ACTIVE tattoos with such name in tattoos catalog.", e);
            throw new DaoException("Error during searching ACTIVE tattoos with such name in tattoos catalog.", e);
        }
    }

    public List<Tattoo> findByPlaceAllActive(String place) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PLACE_AND_STATUS)) {
            statement.setString(1, place);
            statement.setString(2, TattooStatus.ACTIVE.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching ACTIVE tattoos with such place in tattoos catalog.", e);
            throw new DaoException("Error during searching ACTIVE tattoos with such place in tattoos catalog.", e);
        }
    }

    public List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PRICE_RANGE_AND_STATUS)) {
            statement.setBigDecimal(1, min);
            statement.setBigDecimal(2, max);
            statement.setString(3, TattooStatus.ACTIVE.name());
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching ACTIVE tattoos in a given price range in tattoos catalog.", e);
            throw new DaoException("Error during searching ACTIVE tattoos in a given price range in tattoos catalog.", e);
        }
    }

    public List<Tattoo> findByStatus(String status) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_STATUS)) {
            statement.setString(1, status);
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching tattoos with status: " + status, e);
            throw new DaoException("Error during searching tattoos with status: " + status, e);
        }
    }

    public List<Tattoo> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_TATTOOS)) {
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for tattoos catalog.", e);
            throw new DaoException("Error during searching for tattoos catalog.", e);
        }
    }

    public List<Tattoo> findAllActive() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_TATTOOS)) {
            statement.setString(1, TattooStatus.ACTIVE.toString());
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching ACTIVE tattoos in tattoos catalog.", e);
            throw new DaoException("Error during searching ACTIVE tattoos in tattoos catalog.", e);
        }
    }

    public List<Tattoo> findNumberActive(int number) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_ACTIVE_TATTOOS)) {
            statement.setString(1, TattooStatus.ACTIVE.name());
            try (ResultSet resultSet = statement.executeQuery()){
                List<Tattoo> tattoosList = new ArrayList<>();
                while (resultSet.next() && tattoosList.size() < number) {
                    TattooBuilder tattooBuilder = new TattooBuilder();
                    tattooBuilder.setCatalogId(resultSet.getLong(ColumnName.TATTOOS_ID));
                    tattooBuilder.setName(resultSet.getString(ColumnName.TATTOOS_NAME));
                    tattooBuilder.setDescription(resultSet.getString(ColumnName.TATTOOS_DESCRIPTION));
                    tattooBuilder.setPrice(resultSet.getBigDecimal(ColumnName.TATTOOS_PRICE));
                    tattooBuilder.setWidth(resultSet.getInt(ColumnName.TATTOOS_WIDTH));
                    tattooBuilder.setHeight(resultSet.getInt(ColumnName.TATTOOS_HEIGHT));
                    tattooBuilder.setImageUrl(resultSet.getString(ColumnName.TATTOOS_IMAGE_URL));
                    tattooBuilder.setStatus(TattooStatus.valueOf(resultSet.getString(ColumnName.TATTOOS_STATUS)));
                    tattooBuilder.setPlaces(BodyPart.valueOf(resultSet.getString(ColumnName.TATTOOS_PLACE)));
                    tattooBuilder.setUserId(resultSet.getLong(ColumnName.TATTOOS_USER_ID));
                    tattoosList.add(tattooBuilder.build());
                }
                return tattoosList;
            }
        } catch (SQLException e) {
            logger.error("Error during searching ACTIVE tattoos in tattoos catalog.", e);
            throw new DaoException("Error during searching ACTIVE tattoos in tattoos catalog.", e);
        }
    }

    private List<Tattoo> createTattoos(ResultSet resultSet) throws SQLException {
        List<Tattoo> tattoosList = new ArrayList<>();
        while (resultSet.next()) {
            TattooBuilder tattooBuilder = new TattooBuilder();
            tattooBuilder.setCatalogId(resultSet.getLong(ColumnName.TATTOOS_ID));
            tattooBuilder.setName(resultSet.getString(ColumnName.TATTOOS_NAME));
            tattooBuilder.setDescription(resultSet.getString(ColumnName.TATTOOS_DESCRIPTION));
            tattooBuilder.setPrice(resultSet.getBigDecimal(ColumnName.TATTOOS_PRICE));
            tattooBuilder.setWidth(resultSet.getInt(ColumnName.TATTOOS_WIDTH));
            tattooBuilder.setHeight(resultSet.getInt(ColumnName.TATTOOS_HEIGHT));
            tattooBuilder.setImageUrl(resultSet.getString(ColumnName.TATTOOS_IMAGE_URL));
            tattooBuilder.setStatus(TattooStatus.valueOf(resultSet.getString(ColumnName.TATTOOS_STATUS)));
            tattooBuilder.setPlaces(BodyPart.valueOf(resultSet.getString(ColumnName.TATTOOS_PLACE)));
            tattooBuilder.setUserId(resultSet.getLong(ColumnName.TATTOOS_USER_ID));
            tattoosList.add(tattooBuilder.build());
        }
        return tattoosList;
    }
}
