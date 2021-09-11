package edu.epam.task6.model.dao;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.model.entity.Comment;

import java.util.List;
import java.util.Map;

public interface CommentDao {

    boolean add(Map<String, String> parameters) throws DaoException;

    boolean delete(Long commentId) throws DaoException;

    List<Comment> findAll() throws DaoException;
}
