package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CompleteOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        OrderService orderService = OrderServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
            Optional<Order> order = Optional.empty();
            if (userRole == UserRole.ADMIN) {
                order = orderService.findById(orderId);
            }
            if (order.isPresent()) {
                OrderStatus orderStatus = order.get().getOrderStatus();
                if (orderStatus.equals(OrderStatus.ACTIVE)) {
                    orderStatus = OrderStatus.COMPLETED;

                    parameters.put(ColumnName.ORDERS_STATUS, orderStatus.toString());
                    orderService.updateStatus(parameters, orderId);
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                    logger.info("Order status successfully changed to COMPLETED");
                } else {
                    logger.error("Order status is not ACTIVE.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                }
            } else {
                logger.error("Order with this id was not found.");
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
