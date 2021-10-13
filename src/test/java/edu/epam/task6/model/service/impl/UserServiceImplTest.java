package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.impl.UserDaoImpl;
import edu.epam.task6.model.entity.*;
import edu.epam.task6.model.service.UserService;
import org.powermock.reflect.Whitebox;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;

public class UserServiceImplTest {

    private UserService userService;
    private UserDaoImpl userDao;

    private final Map<String, String> parameters = new HashMap<>();
    private final List<User> findList = new ArrayList<>();
    private Optional<User> findOptional = Optional.empty();

    @BeforeClass
    public void setUp() {
        userDao = mock(UserDaoImpl.class);
        userService = UserServiceImpl.getInstance();
        Whitebox.setInternalState(UserDaoImpl.class, "instance", userDao);

        User user = new User(5L, "123.vlad.124@gmail.com", "KOE5", "Владислав", "Макарей", 0, new BigDecimal(5000),
                LocalDateTime.parse("2021-09-11T23:42:45"), 8.5, 16, 672391, UserStatus.ACTIVE, UserRole.USER);

        findOptional = Optional.of(user);
        findList.add(user);
    }

    @AfterClass
    public void tearDown() {
        userDao = null;
        userService = null;
    }

    @BeforeMethod
    public void setUpMethods() {
        parameters.put("email", "123.vlad.124@gmail.com");
        parameters.put("login", "Daethwen");
        parameters.put("password", "12345Something");
        parameters.put("repeat_password", "12345Something");
        parameters.put("name", "Dan");
        parameters.put("surname", "Petrov");
        parameters.put("registration_date", "2021-09-11 23:42:45");
        parameters.put("register_code", "893201");
    }

    @Test
    public void registerUserTestTrue() throws ServiceException, DaoException {
        when(userDao.add(anyMap())).thenReturn(true);
        boolean actual = userService.registerUser(parameters);
        assertTrue(actual);
    }

    @Test
    public void registerUserTestFalse1() throws ServiceException, DaoException {
        String email = "123.vlad.124<script>@gmail.com";
        parameters.computeIfPresent(ColumnName.USER_EMAIL, (key, value) -> value = email);
        when(userDao.add(anyMap())).thenReturn(true);
        boolean actual = userService.registerUser(parameters);
        assertFalse(actual);
    }

    @Test
    public void registerUserTestFalse2() throws DaoException {
        when(userDao.add(parameters)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.registerUser(parameters));
    }

    @Test
    public void updateEmailTestTrue() throws ServiceException, DaoException {
        when(userDao.updateEmail(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateEmail(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateEmailTestFalse1() throws ServiceException, DaoException {
        String email = "123.vlad.124<script>@gmail.com";
        parameters.computeIfPresent(ColumnName.USER_EMAIL, (key, value) -> value = email);
        when(userDao.updateEmail(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateEmail(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updateEmailTestFalse2() throws DaoException {
        when(userDao.updateEmail(parameters, 5L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateEmail(parameters, 5L));
    }

    @Test
    public void updatePasswordTestTrue() throws ServiceException, DaoException {
        when(userDao.updatePassword(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updatePassword(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updatePasswordTestFalse1() throws ServiceException, DaoException {
        String password = "hello<script>";
        parameters.computeIfPresent(ColumnName.USER_PASSWORD, (key, value) -> value = password);
        when(userDao.updatePassword(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updatePassword(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updatePasswordTestFalse2() throws DaoException {
        when(userDao.updatePassword(parameters, 5L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updatePassword(parameters, 5L));
    }

    @Test
    public void updateNameTestTrue() throws ServiceException, DaoException {
        when(userDao.updateName(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateName(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateNameTestFalse1() throws ServiceException, DaoException {
        String name = "В<script>ладислав";
        parameters.computeIfPresent(ColumnName.USER_NAME, (key, value) -> value = name);
        when(userDao.updateName(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateName(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updateNameTestFalse2() throws DaoException {
        when(userDao.updateName(parameters, 5L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateName(parameters, 5L));
    }

    @Test
    public void updateSurnameTestTrue() throws ServiceException, DaoException {
        when(userDao.updateSurname(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateSurname(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateSurnameTestFalse1() throws ServiceException, DaoException {
        String surname = "В<script>кторов";
        parameters.computeIfPresent(ColumnName.USER_SURNAME, (key, value) -> value = surname);
        when(userDao.updateSurname(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateSurname(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updateSurnameTestFalse2() throws DaoException {
        when(userDao.updateSurname(parameters, 5L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateSurname(parameters, 5L));
    }

    @Test
    public void updateDiscountTestTrue() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_DISCOUNT, "10");
        when(userDao.findById(anyLong())).thenReturn(findOptional);
        when(userDao.updateDiscount(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateDiscount(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateDiscountTestFalse1() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_DISCOUNT, "-10");
        when(userDao.updateDiscount(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateDiscount(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updateDiscountTestFalse2() throws DaoException {
        parameters.put(ColumnName.USER_DISCOUNT, "10");
        when(userDao.findById(6L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateDiscount(parameters, 6L));
    }

    @Test
    public void updateDiscountTestFalse3() throws DaoException {
        parameters.put(ColumnName.USER_DISCOUNT, "10");
        when(userDao.updateDiscount(parameters, 6L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateDiscount(parameters, 6L));
    }

    @Test
    public void updateBalanceTestTrue() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_BALANCE, "1000");
        when(userDao.findById(anyLong())).thenReturn(findOptional);
        when(userDao.updateBalance(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateBalance(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateBalanceTestFalse1() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_BALANCE, "something");
        when(userDao.updateBalance(anyMap(), anyLong())).thenReturn(true);
        boolean actual = userService.updateBalance(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updateBalanceTestFalse2() throws DaoException {
        parameters.put(ColumnName.USER_BALANCE, "1000");
        when(userDao.findById(7L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateBalance(parameters, 7L));
    }

    @Test
    public void updateBalanceTestFalse3() throws DaoException {
        parameters.put(ColumnName.USER_BALANCE, "1000");
        when(userDao.updateBalance(parameters, 7L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateBalance(parameters, 7L));
    }

    @Test
    public void updateStatusTestTrue() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_STATUS, "ACTIVE");
        when(userDao.updateStatus(anyInt(), anyLong())).thenReturn(true);
        boolean actual = userService.updateStatus(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateStatusTestFalse() throws DaoException {
        parameters.put(ColumnName.USER_STATUS, "ACTIVE");
        when(userDao.updateStatus(1, 7L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateStatus(parameters, 7L));
    }

    @Test
    public void updateAverageRatingTestTrue() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_AVERAGE_RATING, "7");
        when(userDao.findById(anyLong())).thenReturn(findOptional);
        when(userDao.updateAverageRating(anyDouble(), anyLong())).thenReturn(true);
        when(userDao.updateNumberOfRatings(anyInt(), anyLong())).thenReturn(true);
        boolean actual = userService.updateAverageRating(parameters, 5L);
        assertTrue(actual);
    }

    @Test
    public void updateAverageRatingTestFalse1() throws ServiceException, DaoException {
        parameters.put(ColumnName.USER_AVERAGE_RATING, "12");
        when(userDao.updateAverageRating(anyDouble(), anyLong())).thenReturn(true);
        boolean actual = userService.updateAverageRating(parameters, 5L);
        assertFalse(actual);
    }

    @Test
    public void updateAverageRatingTestFalse2() throws DaoException {
        parameters.put(ColumnName.USER_AVERAGE_RATING, "8");
        when(userDao.findById(7L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateAverageRating(parameters, 7L));
    }

    @Test
    public void updateAverageRatingTestFalse3() throws DaoException {
        parameters.put(ColumnName.USER_AVERAGE_RATING, "9");
        when(userDao.updateAverageRating(8.5, 7L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateAverageRating(parameters, 7L));
    }

    @Test
    public void updateAverageRatingTestFalse4() throws DaoException {
        parameters.put(ColumnName.USER_AVERAGE_RATING, "10");
        when(userDao.updateNumberOfRatings(17, 7L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.updateAverageRating(parameters, 7L));
    }

    @Test
    public void findByIdTestTrue() throws ServiceException, DaoException {
        Optional<User> expected = findOptional;
        when(userDao.findById(anyLong())).thenReturn(findOptional);
        Optional<User> actual = userService.findById(5L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestFalse1() throws ServiceException, DaoException {
        Optional<User> expected = Optional.empty();
        when(userDao.findById(anyLong())).thenReturn(Optional.empty());
        Optional<User> actual = userService.findById(100L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestFalse2() throws DaoException {
        when(userDao.findById(150L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findById(150L));
    }

    @Test
    public void findByLoginTestTrue() throws ServiceException, DaoException {
        Optional<User> expected = findOptional;
        when(userDao.findByLogin(anyString())).thenReturn(findOptional);
        Optional<User> actual = userService.findByLogin("Daethwen");
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginTestFalse1() throws ServiceException, DaoException {
        Optional<User> expected = Optional.empty();
        when(userDao.findByLogin(anyString())).thenReturn(Optional.empty());
        Optional<User> actual = userService.findByLogin("Daet");
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginTestFalse2() throws DaoException {
        when(userDao.findByLogin("Daethwen")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findByLogin("Daethwen"));
    }

    @Test
    public void findByLoginAndPasswordTestFalse1() throws ServiceException, DaoException {
        Optional<User> expected = Optional.empty();
        when(userDao.findByLogin(anyString())).thenReturn(Optional.empty());
        Optional<User> actual = userService.findByLogin("Daet");
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginAndPasswordTestFalse2() throws DaoException {
        when(userDao.findByLogin("Daethwen")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findByLogin("Daethwen"));
    }

    @Test
    public void findByLoginAndPasswordTestFalse3() throws DaoException {
        when(userDao.findPasswordByLogin("Daethwen")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findByLogin("Daethwen"));
    }

    @Test
    public void findByNameTestTrue() throws ServiceException, DaoException {
        List<User> expected = findList;
        when(userDao.findByName(anyString())).thenReturn(findList);
        List<User> actual = userService.findByName("Владислав");
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameTestFalse1() throws ServiceException, DaoException {
        List<User> expected = new ArrayList<>();
        when(userDao.findByName(anyString())).thenReturn(new ArrayList<>());
        List<User> actual = userService.findByName("Михаил");
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameTestFalse2() throws DaoException {
        when(userDao.findByName("Виктор")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findByName("Виктор"));
    }

    @Test
    public void findBySurnameTestTrue() throws ServiceException, DaoException {
        List<User> expected = findList;
        when(userDao.findBySurname(anyString())).thenReturn(findList);
        List<User> actual = userService.findBySurname("Макарей");
        assertEquals(actual, expected);
    }

    @Test
    public void findBySurnameTestFalse1() throws ServiceException, DaoException {
        List<User> expected = new ArrayList<>();
        when(userDao.findBySurname(anyString())).thenReturn(new ArrayList<>());
        List<User> actual = userService.findBySurname("Михаилов");
        assertEquals(actual, expected);
    }

    @Test
    public void findBySurnameTestFalse2() throws DaoException {
        when(userDao.findBySurname("Викторов")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findBySurname("Викторов"));
    }

    @Test
    public void findByStatusTestTrue() throws ServiceException, DaoException {
        List<User> expected = findList;
        when(userDao.findByStatus(anyString())).thenReturn(findList);
        List<User> actual = userService.findByStatus("ACTIVE");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusTestFalse1() throws ServiceException, DaoException {
        List<User> expected = new ArrayList<>();
        when(userDao.findByStatus(anyString())).thenReturn(new ArrayList<>());
        List<User> actual = userService.findByStatus("BLOCKED");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusTestFalse2() throws DaoException {
        when(userDao.findByStatus("ACTIVE")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findByStatus("ACTIVE"));
    }

    @Test
    public void findAllTestFalse1() throws ServiceException, DaoException {
        List<User> expected = new ArrayList<>();
        when(userDao.findAll()).thenReturn(new ArrayList<>());
        List<User> actual = userService.findAll();
        assertEquals(actual, expected);
    }

    @Test
    public void findAllTestFalse2() throws DaoException {
        when(userDao.findAll()).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> userService.findAll());
    }
}
