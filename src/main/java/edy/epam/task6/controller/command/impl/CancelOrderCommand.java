package edy.epam.task6.controller.command.impl;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.ColumnName;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.entity.OrderStatus;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.OrderServiceImpl;
import edy.epam.task6.model.service.impl.UserServiceImpl;
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

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        User userSession = (User) session.getAttribute(SessionAttribute.USER);
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        String userLogin = userSession.getLogin();

        OrderService orderService = new OrderServiceImpl();
        UserService userService = new UserServiceImpl();
        Map<String, String> parameters = new HashMap<>();
        try {
            Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
            Optional<Order> order = orderService.findByIdPersonal(orderId, userLogin);
            if (order.isPresent()) {
                OrderStatus orderStatus = order.get().getOrderStatus();
                if (orderStatus == OrderStatus.ACTIVE) {
                    orderStatus = OrderStatus.CANCELED;

                    BigDecimal paid = order.get().getPaid();
                    BigDecimal balance = paid.add(userSession.getBalance());
                    parameters.put(ColumnName.USER_BALANCE, balance.toString());
                    userService.updateBalance(parameters, userId);
                    userSession.setBalance(balance);
                }
                parameters.put(ColumnName.ORDERS_STATUS, orderStatus.toString());
                if (orderService.updateStatus(parameters, orderId)) {
                    logger.info("Order status change was successful.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                } else {
                    logger.error("An error in changing the order's status.");
                    router = new Router(PagePath.ERROR_PAGE_500);
                }
            } else {
                logger.error("Order with this id and user login was not found.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing status of order: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
