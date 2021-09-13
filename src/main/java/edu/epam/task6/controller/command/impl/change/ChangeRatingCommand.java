package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.OrderStatus;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeRatingCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final Long ADMIN_ID = 1L;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        OrderService orderService = new OrderServiceImpl();
        TattooService tattooService = new TattooServiceImpl();
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(ColumnName.USER_AVERAGE_RATING, request.getParameter(RequestParameter.USER_RATING));
            parameters.put(ColumnName.TATTOOS_AVERAGE_RATING, request.getParameter(RequestParameter.TATTOO_RATING));
            Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
            Optional<Order> order = orderService.findById(orderId);
            if (order.isPresent() && order.get().getOrderStatus().equals(OrderStatus.COMPLETED)) {
                Long tattooId = order.get().getTattooId();
                if (tattooService.updateAverageRating(parameters, tattooId)) {

                    parameters.put(ColumnName.ORDERS_STATUS, OrderStatus.COMPLETED_AND_ASSESSED.toString());
                    orderService.updateStatus(parameters, orderId);
                    if (request.getParameter(RequestParameter.USER_RATING) != null &&
                            !request.getParameter(RequestParameter.USER_RATING).isEmpty()) {

                        if (userService.updateAverageRating(parameters, ADMIN_ID)) {
                            logger.info("Average tattoo and ADMIN USER rating changed successfully.");
                            router = new Router(Router.RouterType.REDIRECT,
                                    session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                        } else {
                            logger.error("Error during changing average rating of user with id = " + ADMIN_ID);
                            request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.RATING);
                            router = new Router(PagePath.CHANGE_PAGE);
                        }
                    } else {
                        logger.info("Average tattoo rating changed successfully.");
                        router = new Router(Router.RouterType.REDIRECT,
                                session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                    }
                } else {
                    logger.error("Error during changing average rating of tattoo with id = " + tattooId);
                    request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.RATING);
                    router = new Router(PagePath.CHANGE_PAGE);
                }
            } else {
                logger.error("Error during changing average rating, order for changing not found. ");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing average rating: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
