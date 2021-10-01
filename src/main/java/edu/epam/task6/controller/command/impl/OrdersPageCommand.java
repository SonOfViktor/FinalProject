package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrdersPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ORDERS_PAGE_ALL_REDIRECT);

        int currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        User user = (User) session.getAttribute(SessionAttribute.USER);
        String userLogin = user.getLogin();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        OrderService orderService = OrderServiceImpl.getInstance();
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
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersOrders(request, orders.size(), currentPage);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_ORDERS_PAGE);
            router = new Router(PagePath.ORDERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to orders page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
