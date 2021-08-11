package edy.epam.task6._main;

import edy.epam.task6.exception.HashGenerationException;
import edy.epam.task6.exception.LocalPropertyException;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.service.TattooService;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.OrderServiceImpl;
import edy.epam.task6.model.service.impl.TattooServiceImpl;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import edy.epam.task6.util.EmailSender;
import edy.epam.task6.util.PropertyReader;
import jdk.swing.interop.SwingInterOpUtils;

import java.math.BigDecimal;
import java.util.*;

import static edy.epam.task6.util.HashGenerator.generatePassword;

public class Main {

    public static final String DATABASE_PROPERTY_PATH = "database.properties";
    public static final String MAIL_PROPERTY_PATH = "mail.properties";
    public static final String DATABASE_URL = "url";
    public static final String DATABASE_DRIVER = "driverClassName";

    public static void main(String[] args) {
        try {
//            TattooService catalogService = new TattooServiceImpl();
//            List<Tattoo> catalogElements =
//                    catalogService.findByPriceRangeAllActive(BigDecimal.valueOf(3000), BigDecimal.valueOf(8000));
//            System.out.println(catalogElements);
//            Optional<Tattoo> tattoo = catalogService.findById(2L);
//            System.out.println(tattoo);
//            Map<String, String> parametersTattoo = new HashMap<>();
//            parametersTattoo.put("name", "Rose");
//            parametersTattoo.put("price", "7800");
//            parametersTattoo.put("width", "30");
//            parametersTattoo.put("height", "30");
//            parametersTattoo.put("image_url", "ref");
//            parametersTattoo.put("tattoo_status", "3");
//            parametersTattoo.put("place", "1");
//            parametersTattoo.put("users_user_id", "2");
//            System.out.println(parametersTattoo);
//            System.out.println(catalogService.AddNewTattoo(parametersTattoo));
            UserService userService = new UserServiceImpl();
//            List<User> userList = userService.findAll();
//            System.out.println(userList);
            Optional<User> user = userService.findByLogin("Daetwen");
            System.out.println(user);
//            List<User> userList = userService.findByLogin("Daetwen");
//            System.out.println(userList);
//            Map<String, String> parameters = new HashMap<>();
//            parameters.put("email", "123.vlad.123.vlad.123@gmail.com");
//            parameters.put("login", "Dimas");
//            parameters.put("password", "2345678abc");
//            parameters.put("name", "Vlad");
//            parameters.put("surname", "Chel");
//            parameters.put("registration_date", "2021-08-08 15:57:00.0");
//            System.out.println(parameters);
//            System.out.println(userService.registerUser(parameters));
//            Map<String, String> parameters2 = new HashMap<>();
//            parameters2.put("balance", "5900");
//            userService.updateBalance(parameters2, 4L);
//            Optional<User> user = userService.findById(4L);
//            System.out.println(user);
//            parameters2.put("password", "23333333356789099090dfgdgf");
//            userService.updatePassword(parameters2, 4L);
//            user = userService.findById(4L);
//            System.out.println(user);

//            try {
//                String resultPassword = generatePassword("12345");
//                System.out.println(resultPassword);
//                System.out.println(resultPassword.length());
//            } catch (HashGenerationException e) {
//                e.getStackTrace();
//            }
//            EmailSender emailSender = new EmailSender();
//            emailSender.sendEmail("Vlad.Korenchyk@gmail.com", "https://vk.com/im?peers=239289588_502084994");
            OrderService orderService = new OrderServiceImpl();
            List<Order> orderList = orderService.findAll();
            System.out.println(orderList);
            Map<String, String> parameters3 = new HashMap<>();
//            parameters3.put("paid", "12000");
//            parameters3.put("registration_date", "2021-08-10 23:51:00.0");
//            parameters3.put("login", "1");
//            parameters3.put("tattoos_tattoo_id", "2");
//            System.out.println(orderService.createOrder(parameters3));

//            parameters3.put("status", "COMPLETED");
//            orderService.updateStatus(parameters3, 2L);
//            orderList = orderService.findAll();
//            System.out.println(orderList);
        } catch (ServiceException e) {
            System.out.println(e.getStackTrace());
        }
//        try {
//            PropertyReader reader = new PropertyReader();
//            Properties properties = reader.read(MAIL_PROPERTY_PATH);
//            System.out.println(properties);
//        } catch (LocalPropertyException e) {
//            e.getStackTrace();
//        }

    }
}
