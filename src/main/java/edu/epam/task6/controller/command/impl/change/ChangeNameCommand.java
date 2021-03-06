package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ChangeNameCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(ColumnName.USER_NAME, request.getParameter(RequestParameter.USER_NAME));
            if (userService.updateName(parameters, userId)) {
                user.setName(request.getParameter(RequestParameter.USER_NAME));
                session.setAttribute(SessionAttribute.USER, user);

                router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
                logger.info("Name updated successfully.");
            } else {
                logger.error("Incorrect data was sent to update name, data validation failed.");
                request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_NAME);
                router = new Router(PagePath.CHANGE_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing name of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
