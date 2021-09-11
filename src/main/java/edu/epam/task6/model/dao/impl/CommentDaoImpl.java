package edu.epam.task6.model.dao.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.builder.CommentBuilder;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.CommentDao;
import edu.epam.task6.model.entity.Comment;
import edu.epam.task6.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.intellij.lang.annotations.Language;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommentDaoImpl implements CommentDao {
    private static final Logger logger = LogManager.getLogger();

    private static CommentDaoImpl instance;

    @Language("SQL")
    //CREATE REGEX
    private static final String CREATE_COMMENT = """
            INSERT INTO comments (text, registration_date, users_user_id)
            VALUES (?, ?, ?)""";
    //FIND REGEX
    private static final String FIND_ALL = """
            SELECT comment_id, text, registration_date, users_user_id
            FROM comments
            ORDER BY comment_id DESC""";
    //DELETE REGEX
    private static final String DELETE_COMMENT = "DELETE FROM comments WHERE comment_id=?";

    private CommentDaoImpl(){}

    public static CommentDaoImpl getInstance(){
        if (instance == null) {
            instance = new CommentDaoImpl();
        }
        return instance;
    }

    public boolean add(Map<String, String> parameters) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_COMMENT)) {
            statement.setString(1, parameters.get(ColumnName.COMMENT_TEXT));
            statement.setString(2, parameters.get(ColumnName.COMMENT_REGISTRATION_DATE));
            statement.setString(3, parameters.get(ColumnName.COMMENT_USER_ID));
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during adding user.", e);
            throw new DaoException("Error during adding user.", e);
        }
        return result;
    }

    public boolean delete(Long commentId) throws DaoException {
        boolean result;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_COMMENT)) {
            statement.setLong(1, commentId);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("Error during deleting comment", e);
            throw new DaoException(e);
        }
        return result;
    }

    public List<Comment> findAll() throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {
            try (ResultSet resultSet = statement.executeQuery()){
                return createComment(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Error during searching for all comments.", e);
            throw new DaoException("Error during searching for all comments.", e);
        }
    }

    private List<Comment> createComment(ResultSet resultSet) throws SQLException {
        List<Comment> commentList = new ArrayList<>();
        while (resultSet.next()) {
            CommentBuilder commentBuilder = new CommentBuilder();
            commentBuilder.setCommentId(resultSet.getLong(ColumnName.COMMENT_ID));
            commentBuilder.setText(resultSet.getString(ColumnName.COMMENT_TEXT));
            Timestamp time = Timestamp.valueOf(resultSet.getString(ColumnName.COMMENT_REGISTRATION_DATE));
            LocalDateTime localDateTime = LocalDateTime.of(
                    time.toLocalDateTime().toLocalDate(),
                    time.toLocalDateTime().toLocalTime());
            commentBuilder.setRegistrationDate(localDateTime);
            commentBuilder.setUserId(resultSet.getLong(ColumnName.COMMENT_USER_ID));
            commentList.add(commentBuilder.build());
        }
        return commentList;
    }
}
