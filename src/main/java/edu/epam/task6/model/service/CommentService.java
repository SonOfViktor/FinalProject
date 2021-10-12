package edu.epam.task6.model.service;

import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Comment;

import java.util.List;
import java.util.Map;

/**
 * The interface Comment service.
 */
public interface CommentService {

    /**
     * Leave comment with Map of parameters.
     *
     * @param parameters the parameters
     * @return the boolean result of leaving a comment
     * @throws ServiceException the service exception
     */
    boolean leaveComment(Map<String, String> parameters) throws ServiceException;

    /**
     * Delete comment by comment id.
     *
     * @param commentId the comment id
     * @return the boolean result of deleting a comment
     * @throws ServiceException the service exception
     */
    boolean deleteComment(Long commentId) throws ServiceException;

    /**
     * Find list of all comments.
     *
     * @return the list of Comment
     * @throws ServiceException the service exception
     */
    List<Comment> findAll() throws ServiceException;
}
