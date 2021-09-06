package edy.epam.task6.controller.command.impl.change;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.ColumnName;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeEmailCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = new UserServiceImpl();
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(ColumnName.USER_EMAIL, request.getParameter(RequestParameter.USER_EMAIL));
            if (userService.updateEmail(parameters, userId)) {
                user.setEmail(request.getParameter(RequestParameter.USER_EMAIL));
                session.setAttribute(SessionAttribute.USER, user);

                router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
                logger.info("Email updated successfully.");
            } else {
                logger.error("Incorrect data was sent to update email, data validation failed.");
                request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_EMAIL);
                router = new Router(PagePath.CHANGE_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing email of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
