package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import edu.epam.task6.util.EmailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CancelOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL_MESSAGE_TITLE = "Order cancellation";
    private static final String EMAIL_MESSAGE_TEXT = "Your order number  has been canceled.";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(SessionAttribute.USER);
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);
        String userLogin = userSession.getLogin();
        try {
            Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
            Optional<Order> order = findNecessaryOrder(userRole, orderId, userLogin);
            if (order.isPresent()) {
                router = update(session, order.get(), userSession, orderId);
            } else {
                logger.error("Order with this id and user login was not found.");
                router = new Router(Router.RouterType.REDIRECT,
                        session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            }
        } catch (ServiceException e) {
            logger.error("Error during changing status of order: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private Optional<Order> findNecessaryOrder(UserRole userRole, Long orderId, String userLogin)
            throws ServiceException {
        Optional<Order> order = Optional.empty();
        OrderService orderService = OrderServiceImpl.getInstance();
        if (userRole == UserRole.ADMIN) {
            order = orderService.findById(orderId);
        } else if (userRole == UserRole.USER) {
            order = orderService.findByIdPersonal(orderId, userLogin);
        }
        return order;
    }

    private Router update(HttpSession session,
                          Order localOrder,
                          User userSession,
                          Long orderId) throws ServiceException {
        Router router;
        OrderStatus orderStatus = localOrder.getOrderStatus();
        if (orderStatus == OrderStatus.ACTIVE) {
            orderStatus = OrderStatus.CANCELED;
            router = updateDependentParameters(session, localOrder, orderStatus, userSession, orderId);
        } else {
            logger.error("Order status is CANCELED already.");
            router = new Router(Router.RouterType.REDIRECT,
                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
        }
        return router;
    }

    private Router updateDependentParameters(HttpSession session,
                                             Order localOrder,
                                             OrderStatus orderStatus,
                                             User userSession,
                                             Long orderId) throws ServiceException {
        Router router;
        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        Optional<User> user = userService.findByLogin(localOrder.getUserLogin());
        if (user.isPresent()) {
            Long userId = user.get().getUserId();
            BigDecimal paid = localOrder.getPaid();
            parameters.put(ColumnName.USER_BALANCE, paid.toString());
            parameters.put(ColumnName.ORDERS_STATUS, orderStatus.toString());
            router = tryToUpdateOrderStatusAndUserBalance(
                    session, parameters, userSession, orderId, userId);
        } else {
            logger.error("User was not found to cancel order.");
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private Router tryToUpdateOrderStatusAndUserBalance(HttpSession session,
                                                    Map<String, String> parameters,
                                                    User userSession,
                                                    Long orderId,
                                                    Long userId) throws ServiceException {
        Router router;
        OrderService orderService = OrderServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        if (orderService.updateStatus(parameters, orderId)
                && userService.updateBalance(parameters, userId)) {

            Optional<User> user = userService.findById(userId);
            userSession.setBalance(user.get().getBalance());
            sendLetterAboutTheCancellationOfTheOrder(user.get(), orderId);
            router = new Router(Router.RouterType.REDIRECT,
                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            logger.info("Order status change was successful.");
        } else {
            router = new Router(PagePath.ERROR_PAGE_500);
            logger.error("An error in changing the order's status.");
        }
        return router;
    }

    private void sendLetterAboutTheCancellationOfTheOrder(User localUser, Long orderId) {
        StringBuilder stringBuilder = new StringBuilder(EMAIL_MESSAGE_TEXT);
        stringBuilder.insert(18, orderId);
        EmailSender emailSender = new EmailSender(
                localUser.getEmail(),
                EMAIL_MESSAGE_TITLE,
                stringBuilder.toString());
        emailSender.start();
    }
}
