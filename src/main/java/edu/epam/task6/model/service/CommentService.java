package edu.epam.task6.model.service;

import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentService {

    boolean leaveComment(Map<String, String> parameters) throws ServiceException;

    boolean deleteComment(Long commentId) throws ServiceException;

    List<Comment> findAll() throws ServiceException;
}
