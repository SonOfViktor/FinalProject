package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.entity.OrderStatus;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class GoToOrdersActivePageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ORDERS_PAGE_ACTIVE_REDIRECT);
        OrderService orderService = new OrderServiceImpl();
        try {
            List<Order> orders = orderService.findByStatus(OrderStatus.ACTIVE.name());
            request.setAttribute(RequestParameter.ORDERS, orders);

            request = SendSplitParameters.sendSplitParametersOrders(request, orders.size());
            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_ACTIVE);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
