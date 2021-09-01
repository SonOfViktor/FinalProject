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

public class ChangePasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = new UserServiceImpl();
        try {
            Optional<User> localUser = userService.findByLoginAndPassword(
                    user.getLogin(),
                    request.getParameter(RequestParameter.PASSWORD_OLD));

            if (localUser.isPresent()) {
                Map<String, String> parameters = new HashMap<>();
                parameters.put(ColumnName.USER_PASSWORD,
                        request.getParameter(RequestParameter.PASSWORD_NEW));
                userService.updatePassword(parameters, userId);
                Optional<User> resultUser = userService.findById(userId);
                request.setAttribute(RequestParameter.PROFILE, resultUser.get());
                switch (user.getRole()) {
                    case ADMIN -> {
                        router = new Router(PagePath.PROFILE_PAGE_ADMIN);
                    }
                    case USER -> {
                        router = new Router(PagePath.PROFILE_PAGE_USER);
                    }
                    default -> {
                        logger.error("Error after change password with a return to the profile page.");
                        router = new Router(PagePath.ERROR_PAGE_500);
                    }
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
