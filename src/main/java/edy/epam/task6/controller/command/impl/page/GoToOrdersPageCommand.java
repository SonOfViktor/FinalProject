package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.entity.UserRole;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class GoToOrdersPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ORDERS_PAGE_ALL_REDIRECT);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String userLogin = user.getLogin();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        OrderService orderService = new OrderServiceImpl();
        try {
            List<Order> orders;
            if (userRole == UserRole.ADMIN) {
                request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_ALL);
                orders = orderService.findAll();
            } else {
                request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_PERSON);
                orders = orderService.findByLogin(userLogin);
            }
            request.setAttribute(RequestParameter.ORDERS, orders);

            request = SendSplitParameters.sendSplitParametersOrders(request, orders.size());
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
