package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;

import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
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

public class ChangeUserStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
         UserService userService = UserServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            Long userId = Long.valueOf(request.getParameter(RequestParameter.USER_ID));
            boolean button = Boolean.parseBoolean(
                    request.getParameter(RequestParameter.USER_BUTTON));
            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                UserStatus userStatus = user.get().getStatus();
                UserRole userRole = user.get().getRole();
                if (userRole != UserRole.ADMIN) {
                    if (userStatus == UserStatus.ACTIVE && !button) {
                        userStatus = UserStatus.BLOCKED;
                    } else if (userStatus == UserStatus.BLOCKED && button) {
                        userStatus = UserStatus.ACTIVE;
                    }
                }
                parameters.put(ColumnName.USER_STATUS, userStatus.toString());
                if (userService.updateStatus(parameters, userId)) {
                    logger.info("User status change was successful.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                } else {
                    logger.error("An error in changing the user's status.");
                    router = new Router(PagePath.ERROR_PAGE_500);
                }
            } else {
                logger.error("User with this login was not found.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing status of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
