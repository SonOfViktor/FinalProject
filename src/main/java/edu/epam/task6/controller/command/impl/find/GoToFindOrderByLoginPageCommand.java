package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.util.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToFindOrderByLoginPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        Integer currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.valueOf(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        OrderService orderService = new OrderServiceImpl();
        String orderLogin = request.getParameter(RequestParameter.USER_LOGIN);
        try {
            List<Order> orders = orderService.findByLogin(orderLogin);
            request.setAttribute(RequestParameter.ORDERS, orders);
            request = SendSplitParameters.sendSplitParametersOrders(request, orders.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_FOUNDED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_FIND_ORDER_BY_LOGIN_PAGE_COMMAND);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching orders with user login = " + orderLogin, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
