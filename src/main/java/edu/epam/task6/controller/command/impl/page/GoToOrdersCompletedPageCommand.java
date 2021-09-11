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

import java.util.List;

public class GoToOrdersCompletedPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ORDERS_PAGE_COMPLETED_REDIRECT);
        OrderService orderService = new OrderServiceImpl();
        try {
            List<Order> orders = orderService.findByStatus(OrderStatus.COMPLETED.name());
            request.setAttribute(RequestParameter.ORDERS, orders);

            request = SendSplitParameters.sendSplitParametersOrders(request, orders.size());
            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_COMPLETED);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}