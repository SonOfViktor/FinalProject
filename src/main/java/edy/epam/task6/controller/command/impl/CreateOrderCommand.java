package edy.epam.task6.controller.command.impl;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.ColumnName;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CreateOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute(SessionAttribute.USER_ID);
        OrderService orderService = new OrderServiceImpl();
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put(ColumnName.ORDERS_PAID,
                    request.getParameter(RequestParameter.ORDER_PAID));
            parameters.put(ColumnName.ORDERS_REGISTRATION_DATE,
                    request.getParameter(RequestParameter.ORDER_REGISTRATION_DATE));
            parameters.put(ColumnName.ORDERS_USER_ID, userId.toString());
            parameters.put(ColumnName.ORDERS_TATTOO_ID,
                    request.getParameter(RequestParameter.ORDER_TATTOO_ID));

            System.out.println(parameters);
            boolean result = orderService.createOrder(parameters);
            if (result) {
                logger.info("Order has been created.");
                router = new Router(PagePath.MAIN_PAGE);
            } else {
                logger.error("Error during creating order.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }

        } catch (ServiceException e) {
            logger.error("Error during creating order: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
