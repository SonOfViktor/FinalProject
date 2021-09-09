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

        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();
        Map<String, String> parameters = new HashMap<>();
        try {
            Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
            Optional<Order> order = Optional.empty();
            if (userRole == UserRole.ADMIN) {
                order = orderService.findById(orderId);
            } else if (userRole == UserRole.USER) {
                order = orderService.findByIdPersonal(orderId, userLogin);
            }
            if (order.isPresent()) {
                OrderStatus orderStatus = order.get().getOrderStatus();
                if (orderStatus == OrderStatus.ACTIVE) {
                    orderStatus = OrderStatus.CANCELED;

                    Optional<User> user = userService.findByLogin(order.get().getUserLogin());
                    BigDecimal paid = order.get().getPaid();
                    BigDecimal balance = paid.add(user.get().getBalance());
                    parameters.put(ColumnName.USER_BALANCE, balance.toString());

                    userService.updateBalance(parameters, user.get().getUserId());
                    userSession.setBalance(balance);

                    parameters.put(ColumnName.ORDERS_STATUS, orderStatus.toString());
                    if (orderService.updateStatus(parameters, orderId)) {

                        StringBuffer sb = new StringBuffer(EMAIL_MESSAGE_TEXT);
                        sb.insert(18, orderId);
                        EmailSender emailSender = new EmailSender(
                                userSession.getEmail(),
                                EMAIL_MESSAGE_TITLE,
                                sb.toString());
                        emailSender.start();
                        router = new Router(Router.RouterType.REDIRECT,
                                session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                        logger.info("Order status change was successful.");
                    } else {
                        logger.error("An error in changing the order's status.");
                        router = new Router(PagePath.ERROR_PAGE_500);
                    }
                } else {
                    logger.error("Order status is CANCELED already.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                }
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
}
