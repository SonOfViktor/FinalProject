package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.impl.OrderDaoImpl;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.service.OrderService;
import org.powermock.reflect.Whitebox;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class OrderServiceImplTest {

    private OrderService orderService;
    private OrderDaoImpl orderDao;

    private final Map<String, String> parameters = new HashMap<>();
    private final Map<String, String> parameters2 = new HashMap<>();
    private final Map<String, String> parameters3 = new HashMap<>();
    private final Map<String, String> parameters4 = new HashMap<>();
    private final List<Order> findList = new ArrayList<>();
    private Optional<Order> findOptional = Optional.empty();

    @BeforeClass
    public void setUp() {
        orderDao = mock(OrderDaoImpl.class);
        orderService = OrderServiceImpl.getInstance();
        Whitebox.setInternalState(OrderDaoImpl.class, "instance", orderDao);

        parameters.put("paid", "5000");
        parameters.put("registration_date", "2021-08-10 23:51:00");
        parameters.put("users_user_id", "1");
        parameters.put("tattoos_tattoo_id", "2");

        parameters2.put("paid", "5000");
        parameters2.put("registration_date", "2021-08-10 23:51:00");
        parameters2.put("users_user_id", "1");
        parameters2.put("tattoos_tattoo_id", "<script>");

        parameters3.put("paid", "5000");
        parameters3.put("registration_date", "2021-08-10 23:51:00");
        parameters3.put("users_user_id", "-1");
        parameters3.put("tattoos_tattoo_id", "2");

        parameters4.put("paid", "-5000");
        parameters4.put("registration_date", "2021-08-10 23:51:00");
        parameters4.put("users_user_id", "1");
        parameters4.put("tattoos_tattoo_id", "2");

        BigDecimal paid = new BigDecimal(5000);
        Order order = new Order(5L, paid, LocalDateTime.parse("2021-09-11T23:42:45"),
                "Daethwen", OrderStatus.ACTIVE, 6L, paid, "Something");
        findOptional = Optional.of(order);
        findList.add(order);
    }

    @AfterClass
    public void tearDown() {
        orderDao = null;
        orderService = null;
    }

    @Test
    public void createOrderTestTrue() throws ServiceException, DaoException {
        when(orderDao.add(anyMap())).thenReturn(true);
        boolean actual = orderService.createOrder(parameters);
        assertTrue(actual);
    }

    @Test
    public void createOrderTestFalse1() throws ServiceException, DaoException {
        when(orderDao.add(anyMap())).thenReturn(true);
        boolean actual = orderService.createOrder(parameters2);
        assertFalse(actual);
    }

    @Test
    public void createOrderTestFalse2() throws ServiceException, DaoException {
        when(orderDao.add(anyMap())).thenReturn(true);
        boolean actual = orderService.createOrder(parameters3);
        assertFalse(actual);
    }

    @Test
    public void createOrderTestFalse3() throws ServiceException, DaoException {
        when(orderDao.add(anyMap())).thenReturn(true);
        boolean actual = orderService.createOrder(parameters4);
        assertFalse(actual);
    }

    @Test
    public void findByIdTestTrue() throws ServiceException, DaoException {
        Optional<Order> expected = findOptional;
        when(orderDao.findById(anyLong())).thenReturn(findOptional);
        Optional<Order> actual = orderService.findById(5L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestFalse1() throws ServiceException, DaoException {
        Optional<Order> expected = Optional.empty();
        when(orderDao.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Order> actual = orderService.findById(100L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestFalse2() throws DaoException {
        when(orderDao.findById(150L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> orderService.findById(150L));
    }

    @Test
    public void findByLoginTestTrue() throws ServiceException, DaoException {
        List<Order> expected = findList;
        when(orderDao.findByLogin(anyString())).thenReturn(findList);
        List<Order> actual = orderService.findByLogin("Daethwen");
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginTestFalse1() throws ServiceException, DaoException {
        List<Order> expected = new ArrayList<>();
        when(orderDao.findByLogin(anyString())).thenReturn(new ArrayList<>());
        List<Order> actual = orderService.findByLogin("Daethwen2");
        assertEquals(actual, expected);
    }

    @Test
    public void findByLoginTestFalse2() throws DaoException {
        when(orderDao.findByLogin("Daethwen3")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> orderService.findByLogin("Daethwen3"));
    }

    @Test
    public void findByStatusTestTrue() throws ServiceException, DaoException {
        List<Order> expected = findList;
        when(orderDao.findByStatus(anyString())).thenReturn(findList);
        List<Order> actual = orderService.findByStatus("ACTIVE");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusTestFalse1() throws ServiceException, DaoException {
        List<Order> expected = new ArrayList<>();
        when(orderDao.findByStatus(anyString())).thenReturn(new ArrayList<>());
        List<Order> actual = orderService.findByStatus("BLOCKED");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusTestFalse2() throws DaoException {
        when(orderDao.findByStatus("CANCALED")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> orderService.findByStatus("CANCALED"));
    }

    @Test
    public void findByIdPersonalTestTrue() throws ServiceException, DaoException {
        Optional<Order> expected = findOptional;
        when(orderDao.findByIdPersonal(anyLong(), anyString())).thenReturn(findOptional);
        Optional<Order> actual = orderService.findByIdPersonal(5L, "Daethwen");
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdPersonalFalse1() throws ServiceException, DaoException {
        Optional<Order> expected = Optional.empty();
        when(orderDao.findByIdPersonal(anyLong(), anyString())).thenReturn(Optional.empty());
        Optional<Order> actual = orderService.findByIdPersonal(5L, "Daet");
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdPersonalFalse2() throws DaoException {
        when(orderDao.findByIdPersonal(150L, "Daethwen")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> orderService.findByIdPersonal(150L, "Daethwen"));
    }

    @Test
    public void findByStatusPersonalTestTrue() throws ServiceException, DaoException {
        List<Order> expected = findList;
        when(orderDao.findByStatusPersonal(anyString(), anyString())).thenReturn(findList);
        List<Order> actual = orderService.findByStatusPersonal("ACTIVE", "Daethwen");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusPersonalTestFalse1() throws ServiceException, DaoException {
        List<Order> expected = new ArrayList<>();
        when(orderDao.findByStatusPersonal(anyString(), anyString())).thenReturn(new ArrayList<>());
        List<Order> actual = orderService.findByStatusPersonal("BLOCKED", "Daet");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusPersonalTestFalse2() throws DaoException {
        when(orderDao.findByStatusPersonal("CANCALED", "Daethwen")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> orderService.findByStatusPersonal("CANCALED", "Daethwen"));
    }

    @Test
    public void findAllTestFalse() throws DaoException {
        when(orderDao.findAll()).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> orderService.findAll());
    }

}
