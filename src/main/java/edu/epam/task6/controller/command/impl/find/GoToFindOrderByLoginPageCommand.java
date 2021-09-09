package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.controller.command.impl.pagination.SendSplitParameters;
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
        OrderService orderService = new OrderServiceImpl();
        String orderLogin = request.getParameter(RequestParameter.USER_LOGIN);
        try {
            List<Order> orders = orderService.findByLogin(orderLogin);
            request.setAttribute(RequestParameter.ORDERS, orders);

            request = SendSplitParameters.sendSplitParametersOrders(request, orders.size());

            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_FOUNDED);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching orders with user login = " + orderLogin, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
