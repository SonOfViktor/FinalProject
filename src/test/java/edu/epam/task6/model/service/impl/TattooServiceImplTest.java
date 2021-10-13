package edu.epam.task6.model.service.impl;

import edu.epam.task6.exception.DaoException;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.dao.impl.TattooDaoImpl;
import edu.epam.task6.model.entity.BodyPart;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;
import edu.epam.task6.model.service.TattooService;
import org.powermock.reflect.Whitebox;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;


public class TattooServiceImplTest {

    private TattooService tattooService;
    private TattooDaoImpl tattooDao;

    private final Map<String, String> parameters = new HashMap<>();
    private final List<Tattoo> findList = new ArrayList<>();
    private Optional<Tattoo> findOptional = Optional.empty();

    @BeforeClass
    public void setUp() {
        tattooDao = mock(TattooDaoImpl.class);
        tattooService = TattooServiceImpl.getInstance();
        Whitebox.setInternalState(TattooDaoImpl.class, "instance", tattooDao);

        Tattoo tattoo = new Tattoo(5L, "Dragon", "Какой-нибудь текст.", new BigDecimal(5000), 20, 30, 8.5, 10,
                "https://www.pinterest.com/pin/896990450753304286/", TattooStatus.ACTIVE, BodyPart.HAND, 10L);

        findOptional = Optional.of(tattoo);
        findList.add(tattoo);
    }

    @AfterClass
    public void tearDown() {
        tattooDao = null;
        tattooService = null;
    }

    @BeforeMethod
    public void setUpMethods() {
        parameters.put("name", "Dragon");
        parameters.put("description", "Какой-нибудь текст.");
        parameters.put("price", "5000");
        parameters.put("width", "20");
        parameters.put("height", "30");
        parameters.put("image_url", "https://www.pinterest.com/pin/896990450753304286/");
        parameters.put("tattoo_status", "1");
        parameters.put("place", "1");
        parameters.put("users_user_id", "1");
    }

    @Test
    public void addNewTattooTestTrue() throws ServiceException, DaoException {
        when(tattooDao.add(anyMap())).thenReturn(true);
        boolean actual = tattooService.addNewTattoo(parameters);
        assertTrue(actual);
    }

    @Test
    public void addNewTattooTestFalse1() throws ServiceException, DaoException {
        String name = "Something <script>";
        parameters.computeIfPresent(ColumnName.TATTOOS_NAME, (key, value) -> value = name);
        when(tattooDao.add(anyMap())).thenReturn(true);
        boolean actual = tattooService.addNewTattoo(parameters);
        assertFalse(actual);
    }

    @Test
    public void addNewTattooTestFalse2() throws ServiceException, DaoException {
        String description = "Something <script>";
        parameters.computeIfPresent(ColumnName.TATTOOS_DESCRIPTION, (key, value) -> value = description);
        when(tattooDao.add(anyMap())).thenReturn(true);
        boolean actual = tattooService.addNewTattoo(parameters);
        assertFalse(actual);
    }

    @Test
    public void addNewTattooTestFalse3() throws ServiceException, DaoException {
        String price = "-5000";
        parameters.computeIfPresent(ColumnName.TATTOOS_PRICE, (key, value) -> value = price);
        when(tattooDao.add(anyMap())).thenReturn(true);
        boolean actual = tattooService.addNewTattoo(parameters);
        assertFalse(actual);
    }

    @Test
    public void addNewTattooTestFalse4() throws ServiceException, DaoException {
        String width = "-20";
        parameters.computeIfPresent(ColumnName.TATTOOS_WIDTH, (key, value) -> value = width);
        when(tattooDao.add(anyMap())).thenReturn(true);
        boolean actual = tattooService.addNewTattoo(parameters);
        assertFalse(actual);
    }

    @Test
    public void addNewTattooTestFalse5() throws ServiceException, DaoException {
        String link = "http://<script>link";
        parameters.computeIfPresent(ColumnName.TATTOOS_IMAGE_URL, (key, value) -> value = link);
        when(tattooDao.add(anyMap())).thenReturn(true);
        boolean actual = tattooService.addNewTattoo(parameters);
        assertFalse(actual);
    }

    @Test
    public void addNewTattooTestFalse6() throws DaoException {
        when(tattooDao.add(parameters)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.addNewTattoo(parameters));
    }

    @Test
    public void updatePriceTestTrue() throws ServiceException, DaoException {
        Map<String, String> localParameters = new HashMap<>();
        localParameters.put(ColumnName.TATTOOS_PRICE, "5000");
        when(tattooDao.updatePrice(anyObject(), anyLong())).thenReturn(true);
        boolean actual = tattooService.updatePrice(localParameters, 1L);
        assertTrue(actual);
    }

    @Test
    public void updatePriceTestFalse1() throws ServiceException, DaoException {
        Map<String, String> localParameters = new HashMap<>();
        localParameters.put(ColumnName.TATTOOS_PRICE, "-5000");
        when(tattooDao.updatePrice(anyObject(), anyLong())).thenReturn(true);
        boolean actual = tattooService.updatePrice(localParameters, 1L);
        assertFalse(actual);
    }

    @Test
    public void updatePriceTestFalse2() throws DaoException {
        Map<String, String> localParameters = new HashMap<>();
        localParameters.put(ColumnName.TATTOOS_PRICE, "5000");
        when(tattooDao.updatePrice(new BigDecimal(5000), 1L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.updatePrice(localParameters, 1L));
    }

    @Test
    public void findByIdTestTrue() throws ServiceException, DaoException {
        Optional<Tattoo> expected = findOptional;
        when(tattooDao.findById(anyLong())).thenReturn(findOptional);
        Optional<Tattoo> actual = tattooService.findById(5L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestFalse1() throws ServiceException, DaoException {
        Optional<Tattoo> expected = Optional.empty();
        when(tattooDao.findById(anyLong())).thenReturn(Optional.empty());
        Optional<Tattoo> actual = tattooService.findById(100L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdTestFalse2() throws DaoException {
        when(tattooDao.findById(150L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findById(150L));
    }

    @Test
    public void findByNameTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByName(anyString())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByName("Dragon");
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByName(anyString())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByName("Dragon2");
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameTestFalse2() throws DaoException {
        when(tattooDao.findByName("Some")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByName("Some"));
    }

    @Test
    public void findByPriceRangeTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByPriceRange(anyObject(), anyObject())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByPriceRange("5000", "10000");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPriceRangeTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByPriceRange(anyObject(), anyObject())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByPriceRange("100", "200");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPriceRangeTestFalse2() throws DaoException {
        when(tattooDao.findByPriceRange(new BigDecimal(1), new BigDecimal(11000))).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByPriceRange("1", "11000"));
    }

    @Test
    public void findByPlaceTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByPlace(anyString())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByPlace("HAND");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPlaceTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByPlace(anyString())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByPlace("BACK");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPlaceTestFalse2() throws DaoException {
        when(tattooDao.findByPlace("LEG")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByPlace("LEG"));
    }

    @Test
    public void findByIdAllActiveTestTrue() throws ServiceException, DaoException {
        Optional<Tattoo> expected = findOptional;
        when(tattooDao.findByIdAllActive(anyLong())).thenReturn(findOptional);
        Optional<Tattoo> actual = tattooService.findByIdAllActive(5L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdAllActiveTestFalse1() throws ServiceException, DaoException {
        Optional<Tattoo> expected = Optional.empty();
        when(tattooDao.findByIdAllActive(anyLong())).thenReturn(Optional.empty());
        Optional<Tattoo> actual = tattooService.findByIdAllActive(100L);
        assertEquals(actual, expected);
    }

    @Test
    public void findByIdAllActiveTestFalse2() throws DaoException {
        when(tattooDao.findByIdAllActive(150L)).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByIdAllActive(150L));
    }

    @Test
    public void findByNameAllActiveTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByNameAllActive(anyString())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByNameAllActive("Dragon");
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameAllActiveTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByNameAllActive(anyString())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByNameAllActive("Dragon2");
        assertEquals(actual, expected);
    }

    @Test
    public void findByNameAllActiveTestFalse2() throws DaoException {
        when(tattooDao.findByNameAllActive("Some")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByNameAllActive("Some"));
    }

    @Test
    public void findByPlaceAllActiveTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByPlaceAllActive(anyString())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByPlaceAllActive("HAND");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPlaceAllActiveTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByPlaceAllActive(anyString())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByPlaceAllActive("BACK");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPlaceAllActiveTestFalse2() throws DaoException {
        when(tattooDao.findByPlaceAllActive("LEG")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByPlaceAllActive("LEG"));
    }

    @Test
    public void findByPriceRangeAllActiveTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByPriceRangeAllActive(anyObject(), anyObject())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByPriceRangeAllActive("5000", "10000");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPriceRangeAllActiveTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByPriceRangeAllActive(anyObject(), anyObject())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByPriceRangeAllActive("100", "200");
        assertEquals(actual, expected);
    }

    @Test
    public void findByPriceRangeAllActiveTestFalse2() throws DaoException {
        when(tattooDao.findByPriceRangeAllActive(new BigDecimal(1), new BigDecimal(11000))).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByPriceRangeAllActive("1", "11000"));
    }

    @Test
    public void findByStatusTestTrue() throws ServiceException, DaoException {
        List<Tattoo> expected = findList;
        when(tattooDao.findByStatus(anyString())).thenReturn(findList);
        List<Tattoo> actual = tattooService.findByStatus("ACTIVE");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusTestFalse1() throws ServiceException, DaoException {
        List<Tattoo> expected = new ArrayList<>();
        when(tattooDao.findByStatus(anyString())).thenReturn(new ArrayList<>());
        List<Tattoo> actual = tattooService.findByStatus("LOCKED");
        assertEquals(actual, expected);
    }

    @Test
    public void findByStatusTestFalse2() throws DaoException {
        when(tattooDao.findByStatus("LOCKED")).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findByStatus("LOCKED"));
    }


    @Test
    public void findAllTestFalse() throws DaoException {
        when(tattooDao.findAll()).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findAll());
    }

    @Test
    public void findAllActiveTestFalse() throws DaoException {
        when(tattooDao.findAllActive()).thenThrow(DaoException.class);
        Assert.assertThrows(ServiceException.class,
                () -> tattooService.findAllActive());
    }
}
