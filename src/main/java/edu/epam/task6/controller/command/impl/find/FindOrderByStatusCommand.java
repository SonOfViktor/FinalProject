package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindOrderByStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        int currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String userLogin = user.getLogin();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        OrderService orderService = OrderServiceImpl.getInstance();
        if (request.getParameter(RequestParameter.ORDER_STATUS) != null) {
            session.setAttribute(SessionAttribute.FIND_PARAMETER_ONE,
                    request.getParameter(RequestParameter.ORDER_STATUS));
        }
        String orderStatus = session.getAttribute(SessionAttribute.FIND_PARAMETER_ONE).toString();
        try {
            List<Order> orders;
            if (userRole == UserRole.ADMIN) {
                orders = orderService.findByStatus(orderStatus);
            } else {
                orders = orderService.findByStatusPersonal(orderStatus, userLogin);
            }
            request.setAttribute(RequestParameter.ORDERS, orders);
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersOrders(request, orders.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_ORDERS, RequestParameter.TITLE_ORDERS_FOUNDED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_FIND_ORDER_BY_STATUS_PAGE);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching orders with status = " + orderStatus, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
