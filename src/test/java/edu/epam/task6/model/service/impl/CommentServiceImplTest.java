package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.impl.CommentDaoImpl;
import edu.epam.task6.model.entity.Comment;
import edu.epam.task6.model.service.CommentService;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class CommentServiceImplTest {

    private CommentService commentService;
    private CommentDaoImpl commentDao;

    private final Map<String, String> parameters = new HashMap<>();
    private final Map<String, String> parameters2 = new HashMap<>();
    private final Map<String, String> parameters3 = new HashMap<>();
    private final List<Comment> findResult = new ArrayList<>();

    @BeforeClass
    public void setUp() {
        commentDao = mock(CommentDaoImpl.class);
        commentService = CommentServiceImpl.getInstance();
        Whitebox.setInternalState(CommentDaoImpl.class, "instance", commentDao);

        parameters.put("text", "Это тестовый комментарий");
        parameters.put("registration_date", "2021-09-11 23:42:45");
        parameters.put("users_user_id", "1");

        parameters2.put("text", "Это тестовый комментарий 2");
        parameters2.put("registration_date", "2021-09-11 23:42:45");
        parameters2.put("users_user_id", "-1");

        parameters3.put("text", "Это тестовый комментарий со скриптом <script>");
        parameters3.put("registration_date", "2021-09-11 23:42:45");
        parameters3.put("users_user_id", "10");

        Comment comment = new Comment(1L, "Это тестовый комментарий",
                LocalDateTime.parse("2021-09-11T23:42:45"), 1L, "Daethwen");
        findResult.add(comment);
    }

    @AfterClass
    public void tearDown() {
        commentDao = null;
        commentService = null;
    }

    @Test
    public void leaveCommentTestTrue() throws ServiceException, DaoException {
        when(commentDao.add(anyMap())).thenReturn(true);
        boolean actual = commentService.leaveComment(parameters);
        assertTrue(actual);
    }

    @Test
    public void leaveCommentTestFalse1() throws ServiceException, DaoException {
        when(commentDao.add(anyMap())).thenReturn(true);
        boolean actual = commentService.leaveComment(parameters2);
        assertFalse(actual);
    }

    @Test
    public void leaveCommentTestFalse2() throws ServiceException, DaoException {
        when(commentDao.add(anyMap())).thenReturn(true);
        boolean actual = commentService.leaveComment(parameters3);
        assertFalse(actual);
    }

    @Test
    public void findAllTestTrue() throws ServiceException, DaoException {
        List<Comment> expected = findResult;
        when(commentDao.findAll()).thenReturn(findResult);
        List<Comment> actual = commentService.findAll();
        assertEquals(actual, expected);
    }

}
