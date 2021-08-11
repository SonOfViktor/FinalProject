package edy.epam.task6.model.dao;

import edy.epam.task6.exception.DaoException;
import edy.epam.task6.model.builder.TattooBuilder;
import edy.epam.task6.model.entity.BodyPart;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.entity.TattooStatus;
import edy.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class TattooDao {
    private static final Logger logger = LogManager.getLogger();

    private static TattooDao instance;

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
    private static final String FIND_BY_NAME_ALL_ACTIVE_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE name=? AND tattoo_statuses.tattoo_status=?""";
    private static final String FIND_BY_PRICE_RANGE_ALL_ACTIVE_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE price BETWEEN ? AND ? AND tattoo_statuses.tattoo_status=?""";
    private static final String FIND_ALL_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id""";
    private static final String FIND_ALL_ACTIVE_TATTOOS = """
            SELECT tattoo_id, name, description, price, width, height,
            image_url, tattoo_statuses.tattoo_status, body_parts.place, users_user_id
            FROM tattoos
            JOIN tattoo_statuses
            ON tattoos.tattoo_status_id = tattoo_statuses.tattoo_status_id
            JOIN body_parts
            ON tattoos.body_part_id = body_parts.body_part_id
            WHERE tattoo_statuses.tattoo_status=?""";

    private TattooDao(){}

    public static TattooDao getInstance(){
        if (instance == null) {
            instance = new TattooDao();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_TATTOO)) {
            statement.setString(1, parameters.get(FieldName.TATTOOS_NAME));
            statement.setString(2, parameters.get(FieldName.TATTOOS_DESCRIPTION));
            statement.setBigDecimal(3, BigDecimal.valueOf(Long.parseLong(parameters.get(FieldName.TATTOOS_PRICE))));
            statement.setInt(4, Integer.valueOf(parameters.get(FieldName.TATTOOS_WIDTH)));
            statement.setInt(5, Integer.valueOf(parameters.get(FieldName.TATTOOS_HEIGHT)));
            statement.setString(6, parameters.get(FieldName.TATTOOS_IMAGE_URL));
            statement.setInt(7, Integer.valueOf(parameters.get(FieldName.TATTOOS_STATUS)));
            statement.setInt(8, Integer.valueOf(parameters.get(FieldName.TATTOOS_PLACE)));
            statement.setInt(9, Integer.valueOf(parameters.get(FieldName.TATTOOS_USER_ID)));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding tattoo.", e);
            throw new DaoException("Error during adding tattoo.", e);
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
                    tattooBuilder.setCatalogId(resultSet.getLong(FieldName.TATTOOS_ID));
                    tattooBuilder.setName(resultSet.getString(FieldName.TATTOOS_NAME));
                    tattooBuilder.setDescription(resultSet.getString(FieldName.TATTOOS_DESCRIPTION));
                    tattooBuilder.setPrice(resultSet.getBigDecimal(FieldName.TATTOOS_PRICE));
                    tattooBuilder.setWidth(resultSet.getInt(FieldName.TATTOOS_WIDTH));
                    tattooBuilder.setHeight(resultSet.getInt(FieldName.TATTOOS_HEIGHT));
                    tattooBuilder.setImageUrl(resultSet.getString(FieldName.TATTOOS_IMAGE_URL));
                    tattooBuilder.setStatus(TattooStatus.valueOf(resultSet.getString(FieldName.TATTOOS_STATUS)));
                    tattooBuilder.setPlaces(BodyPart.valueOf(resultSet.getString(FieldName.TATTOOS_PLACE)));
                    tattooBuilder.setUserId(resultSet.getLong(FieldName.TATTOOS_USER_ID));
                    resultTattoo = Optional.of(tattooBuilder.build());
                }
                return resultTattoo;
            }
        } catch (SQLException e) {
            logger.error("Error during searching tattoo by ID in catalog.", e);
            throw new DaoException("Error during searching tattoo by ID in catalog.", e);
        }
    }

    public List<Tattoo> findByNameAllActive(String name) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME_ALL_ACTIVE_TATTOOS)) {
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

    public List<Tattoo> findByPriceRangeAllActive(BigDecimal min, BigDecimal max) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_PRICE_RANGE_ALL_ACTIVE_TATTOOS)) {
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
            statement.setString(1, TattooStatus.ACTIVE.name());
            try (ResultSet resultSet = statement.executeQuery()){
                return createTattoos(resultSet);
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
            tattooBuilder.setCatalogId(resultSet.getLong(FieldName.TATTOOS_ID));
            tattooBuilder.setName(resultSet.getString(FieldName.TATTOOS_NAME));
            tattooBuilder.setDescription(resultSet.getString(FieldName.TATTOOS_DESCRIPTION));
            tattooBuilder.setPrice(resultSet.getBigDecimal(FieldName.TATTOOS_PRICE));
            tattooBuilder.setWidth(resultSet.getInt(FieldName.TATTOOS_WIDTH));
            tattooBuilder.setHeight(resultSet.getInt(FieldName.TATTOOS_HEIGHT));
            tattooBuilder.setImageUrl(resultSet.getString(FieldName.TATTOOS_IMAGE_URL));
            tattooBuilder.setStatus(TattooStatus.valueOf(resultSet.getString(FieldName.TATTOOS_STATUS)));
            tattooBuilder.setPlaces(BodyPart.valueOf(resultSet.getString(FieldName.TATTOOS_PLACE)));
            tattooBuilder.setUserId(resultSet.getLong(FieldName.TATTOOS_USER_ID));
            tattoosList.add(tattooBuilder.build());
        }
        return tattoosList;
    }
}
