package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangePasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Optional<User> localUser = userService.findByLoginAndPassword(
                    user.getLogin(),
                    request.getParameter(RequestParameter.PASSWORD_OLD));

            if (localUser.isPresent()) {
                Map<String, String> parameters = new HashMap<>();
                parameters.put(RequestParameter.USER_PASSWORD,
                        request.getParameter(RequestParameter.PASSWORD_NEW));
                if (userService.updatePassword(parameters, userId)) {
                    router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
                    logger.info("Password updated successfully.");
                } else {
                    logger.error("Incorrect data was sent to update password, data validation failed.");
                    request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_PASSWORD);
                    request.setAttribute(RequestParameter.CHANGE_PASSWORD_ERROR, true);
                    router = new Router(PagePath.CHANGE_PAGE);
                }
            }
            else {
                request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_PASSWORD);
                request.setAttribute(RequestParameter.CHANGE_PASSWORD_ERROR, true);
                logger.error("Error in change password command. User with this login and password was not found.");
                router = new Router(PagePath.CHANGE_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing password of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
