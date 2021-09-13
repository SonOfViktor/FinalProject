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
     * Leave comment boolean.
     *
     * @param parameters the parameters
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean leaveComment(Map<String, String> parameters) throws ServiceException;

    /**
     * Delete comment boolean.
     *
     * @param commentId the comment id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean deleteComment(Long commentId) throws ServiceException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findAll() throws ServiceException;
}
