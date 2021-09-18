package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Order;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToOrdersCompletedPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ORDERS_PAGE_COMPLETED_REDIRECT);

        Integer currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.valueOf(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        OrderService orderService = new OrderServiceImpl();
        try {
            List<Order> orders = orderService.findByStatus(OrderStatus.COMPLETED.name());
            request.setAttribute(RequestParameter.ORDERS, orders);
            request = SendSplitParameters.sendSplitParametersOrders(request, orders.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_COMPLETED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_COMPLETED_ORDERS_PAGE_COMMAND);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to completed orders page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
