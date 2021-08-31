package edy.epam.task6.controller.command.impl.find;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Order;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.entity.UserRole;
import edy.epam.task6.model.service.OrderService;
import edy.epam.task6.model.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToFindOrderByStatusPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String userLogin = user.getLogin();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        OrderService orderService = new OrderServiceImpl();
        String orderStatus = request.getParameter(RequestParameter.ORDER_STATUS);
        try {
            List<Order> orders;
            if (userRole == UserRole.ADMIN) {
                orders = orderService.findByStatus(orderStatus);
            } else {
                orders = orderService.findByStatusPersonal(orderStatus, userLogin);
            }
            request.setAttribute(RequestParameter.ORDERS, orders);

            request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                    PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE);

            request.setAttribute(RequestParameter.PAGES_NUMBER,
                    (int)Math.ceil((double)orders.size()
                            / PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE));

            request.setAttribute(RequestParameter.NUMBER_OF_ORDERS, orders.size());

            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_FOUNDED);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching orders with status = " + orderStatus, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
