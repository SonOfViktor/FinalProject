package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.CommentDao;
import edu.epam.task6.model.dao.impl.CommentDaoImpl;
import edu.epam.task6.model.service.CommentService;
import org.mockito.Mockito;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CommentServiceImplTest {

    private CommentService commentService;
    private CommentDao commentDao;

    private Map<String, String> parameters = new HashMap<>();
    private Map<String, String> parameters2 = new HashMap<>();

    @BeforeClass
    public void setUp() {
        commentDao =mock(CommentDao.class);
        commentService = mock(CommentServiceImpl.class);

        parameters.put("text", "Это тестовый комментарий");
        parameters.put("registration_date", "2021-09-11 23:42:45");
        parameters.put("users_user_id", "1");

        parameters2.put("text", "Это тестовый комментарий");
        parameters2.put("registration_date", "2021-09-11 23:42:45");
        parameters2.put("users_user_id", "-1");
    }

    @AfterClass
    public void tearDown() {

    }

    @Test
    public void leaveCommentTestTrue() throws ServiceException, DaoException {
        when(commentDao.add(anyMap())).thenReturn(true);
        boolean actual = commentService.leaveComment(parameters);
        assertTrue(actual);
    }

    @Test
    public void leaveCommentTestFalse() throws ServiceException, DaoException {
        boolean actual = commentService.leaveComment(parameters2);
        assertFalse(actual);
    }

    @Test
    public void leaveCommentTestFalse2() throws ServiceException, DaoException {
        when(commentDao.add(anyMap())).thenReturn(false);
        boolean actual = commentService.leaveComment(parameters);
        assertFalse(actual);
    }
}
