package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GoToFindOrderByIdPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        String userLogin = user.getLogin();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        OrderService orderService = new OrderServiceImpl();
        Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
        try {
            Optional<Order> order;
            if (userRole == UserRole.ADMIN) {
                order = orderService.findById(orderId);
            } else {
                order = orderService.findByIdPersonal(orderId, userLogin);
            }
            if (order.isPresent()) {
                Order localOrder = order.get();
                request.setAttribute(RequestParameter.FIND_ORDER_ERROR, false);
                request.setAttribute(RequestParameter.ORDER, localOrder);
                router = new Router(PagePath.FIND_OPTIONAL_PAGE);
            } else {
                request.setAttribute(RequestParameter.FIND_ORDER_ERROR, true);
                logger.error("Order with this id was not found. id = " + orderId);
                router = new Router(PagePath.FIND_OPTIONAL_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during searching order with id = " + orderId, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
