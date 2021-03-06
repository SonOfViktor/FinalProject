package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CreateOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final BigDecimal DIVIDER = new BigDecimal("100");

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Long tattooId = Long.valueOf(request.getParameter(RequestParameter.ORDER_TATTOO_ID));
        TattooService tattooService = TattooServiceImpl.getInstance();
        try {
            Optional<Tattoo> tattoo = tattooService.findByIdAllActive(tattooId);
            if (tattoo.isPresent()) {
                Tattoo resultTattoo = tattoo.get();
                router = balanceAnalysisAndAttemptToCreateAnOrder(request, resultTattoo);
            } else {
                logger.error("Error during creating order: tattoo with entered id was not found.");
                request.setAttribute(RequestParameter.TATTOO_ID_NOT_FOUND_MESSAGE, true);
                router = new Router(PagePath.CREATE_ORDER_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during creating order: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private Router balanceAnalysisAndAttemptToCreateAnOrder(HttpServletRequest request,
                                                            Tattoo resultTattoo) throws ServiceException {
        Router router;
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(SessionAttribute.USER);
        BigDecimal userDiscount = new BigDecimal(userSession.getDiscount().toString());
        BigDecimal userBalance = userSession.getBalance();
        BigDecimal tattooPrice = resultTattoo.getPrice();
        BigDecimal discount = tattooPrice.multiply(userDiscount).divide(DIVIDER);
        BigDecimal paid = tattooPrice.add(discount.negate());
        userBalance = userBalance.add(discount).add(tattooPrice.negate());
        if (userBalance.compareTo(BigDecimal.ZERO) > 0) {
            router = tryCreateOrder(request, session, userSession, paid, userBalance);
        } else {
            logger.error("Error during creating order: not enough money in the account");
            request.setAttribute(RequestParameter.BALANCE_NOT_ENOUGH_ERROR_MESSAGE, true);
            router = new Router(PagePath.CREATE_ORDER_PAGE);
        }
        return router;
    }

    private Router tryCreateOrder(HttpServletRequest request,
                                  HttpSession session,
                                  User userSession,
                                  BigDecimal paid,
                                  BigDecimal userBalance) throws ServiceException {
        Router router;
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        Long tattooId = Long.valueOf(request.getParameter(RequestParameter.ORDER_TATTOO_ID));
        Map<String, String> parameters = addParameters(request, paid, userId, tattooId);
        OrderService orderService = OrderServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        if (orderService.createOrder(parameters)) {
            userService.updateBalance(parameters, userId);
            userSession.setBalance(userBalance);
            request.setAttribute(RequestParameter.PROFILE, userSession);
            router = new Router(Router.RouterType.REDIRECT,
                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            logger.info("Order has been created.");
        } else {
            router = new Router(PagePath.ERROR_PAGE_500);
            logger.error("Error during creating order.");
        }
        return router;
    }

    private Map<String, String> addParameters(HttpServletRequest request,
                                              BigDecimal paid,
                                              Long userId,
                                              Long tattooId) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ColumnName.USER_BALANCE, paid.negate().toString());
        parameters.put(ColumnName.ORDERS_PAID, paid.toString());
        parameters.put(ColumnName.ORDERS_REGISTRATION_DATE,
                request.getParameter(RequestParameter.ORDER_REGISTRATION_DATE));
        parameters.put(ColumnName.ORDERS_USER_ID, userId.toString());
        parameters.put(ColumnName.ORDERS_TATTOO_ID, tattooId.toString());
        return parameters;
    }
}
