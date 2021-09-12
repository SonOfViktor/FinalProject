package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.CommentDao;
import edu.epam.task6.model.dao.impl.CommentDaoImpl;
import edu.epam.task6.model.entity.Comment;
import edu.epam.task6.model.service.CommentService;
import edu.epam.task6.model.validator.Validator;

import java.util.List;
import java.util.Map;

public class CommentServiceImpl implements CommentService {

    public boolean leaveComment(Map<String, String> parameters) throws ServiceException {
        String text = parameters.get(ColumnName.COMMENT_TEXT);
        boolean result = Validator.validateComment(text);
        if(result) {
            CommentDao commentDao = CommentDaoImpl.getInstance();
            try {
                result = commentDao.add(parameters);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public boolean deleteComment(Long commentId) throws ServiceException {
        boolean result = Validator.validateId(commentId.toString());
        if(result) {
            CommentDao commentDao = CommentDaoImpl.getInstance();
            try {
                result = commentDao.delete(commentId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return result;
    }

    public List<Comment> findAll() throws ServiceException {
        CommentDao commentDao = CommentDaoImpl.getInstance();
        try {
            List<Comment> commentList = commentDao.findAll();
            return commentList;
        } catch (DaoException e){
            throw new ServiceException(e);
        }
    }
}
