package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Order;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrdersActivePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int currentPage = pagesManagement(request, PagePath.ORDERS_PAGE_ACTIVE_REDIRECT);
        OrderService orderService = OrderServiceImpl.getInstance();
        try {
            List<Order> orders = orderService.findByStatus(OrderStatus.ACTIVE.name());
            request.setAttribute(RequestParameter.ORDERS, orders);
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersOrders(request, orders.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_ACTIVE);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_ACTIVE_ORDERS_PAGE);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to active orders page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
