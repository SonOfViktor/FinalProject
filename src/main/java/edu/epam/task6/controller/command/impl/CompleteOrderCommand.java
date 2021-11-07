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
        try {
            Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
            if (userRole == UserRole.ADMIN) {
                router = isOrderFoundedAndChanged(session, orderId);
            } else {
                logger.error("An attempt to change the status of an order without the required access level.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing status of order: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private Router isOrderFoundedAndChanged(HttpSession session, Long orderId)
            throws ServiceException {
        Router router;
        OrderService orderService = OrderServiceImpl.getInstance();
        Optional<Order> order = orderService.findById(orderId);
        if (order.isPresent()) {
            Order localOrder = order.get();
            router = tryToUpdateOrderStatus(session, localOrder, orderId);
        } else {
            router = new Router(Router.RouterType.REDIRECT,
                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            logger.error("Order with this id was not found.");
        }
        return router;
    }

    private Router tryToUpdateOrderStatus(HttpSession session,
                                        Order localOrder,
                                        Long orderId) throws ServiceException {
        Router router;
        OrderService orderService = OrderServiceImpl.getInstance();
        OrderStatus orderStatus = localOrder.getOrderStatus();
        if (orderStatus.equals(OrderStatus.ACTIVE)) {
            orderStatus = OrderStatus.COMPLETED;
            Map<String, String> parameters = new HashMap<>();
            parameters.put(ColumnName.ORDERS_STATUS, orderStatus.toString());
            orderService.updateStatus(parameters, orderId);
            router = new Router(Router.RouterType.REDIRECT,
                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            logger.info("Order status successfully changed to COMPLETED");
        } else {
            router = new Router(Router.RouterType.REDIRECT,
                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            logger.error("Order status is not ACTIVE.");
        }
        return router;
    }
}
