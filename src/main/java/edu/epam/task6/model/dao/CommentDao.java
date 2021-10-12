package edu.epam.task6.model.dao;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * The interface Comment dao.
 */
public interface CommentDao {

    /**
     * Add new comment.
     *
     * @param parameters the parameters
     * @return the boolean result of adding a comment to the database
     * @throws DaoException the dao exception
     */
    boolean add(Map<String, String> parameters) throws DaoException;

    /**
     * Delete comment.
     *
     * @param commentId the comment id
     * @return the boolean result of deleting a comment from the database
     * @throws DaoException the dao exception
     */
    boolean delete(Long commentId) throws DaoException;

    /**
     * Find all comments.
     *
     * @return the list of all comments in the database
     * @throws DaoException the dao exception
     */
    List<Comment> findAll() throws DaoException;
}
