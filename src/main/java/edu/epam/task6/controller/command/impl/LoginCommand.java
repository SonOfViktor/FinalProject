package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserStatus;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        UserService userService = new UserServiceImpl();
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put(ColumnName.USER_LOGIN, request.getParameter(RequestParameter.USER_LOGIN));
            parameters.put(ColumnName.USER_PASSWORD, request.getParameter(RequestParameter.USER_PASSWORD));
            Optional<User> user =
                    userService.findByLoginAndPassword(parameters.get(ColumnName.USER_LOGIN),
                                                       parameters.get(ColumnName.USER_PASSWORD));
            if (user.isPresent()) {
                User localUser = user.get();

                if (localUser.getStatus() == UserStatus.ACTIVE) {
                    request.setAttribute(RequestParameter.PROFILE, localUser);
                    session.setAttribute(SessionAttribute.USER, localUser);
                    session.setAttribute(SessionAttribute.USER_ID, localUser.getUserId());
                    session.setAttribute(SessionAttribute.ROLE, localUser.getRole());
                    session.setAttribute(SessionAttribute.STATUS, localUser.getStatus());
                    session.setAttribute(SessionAttribute.AUTHENTICATION, true);
                    session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.PROFILE_PAGE_REDIRECT);

                    router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
                } else {
                    request.setAttribute(RequestParameter.USER_BLOCKED_ERROR, true);
                    logger.info("This user is blocked.");
                    router = new Router(PagePath.LOGIN_PAGE);
                }
            } else {
                request.setAttribute(RequestParameter.LOGIN_ERROR, true);
                logger.error("User with this login and password was not found.");
                router = new Router(PagePath.LOGIN_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during login user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}