package edy.epam.task6.controller.command.impl.find;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class GoToFindUserByIdPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService userService = new UserServiceImpl();
        Long userId = Long.valueOf(request.getParameter(RequestParameter.USER_ID));
        try {
            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                User localUser = user.get();
                request.setAttribute(RequestParameter.FIND_USER_ERROR, false);
                request.setAttribute(RequestParameter.USER, localUser);
                router = new Router(PagePath.FIND_OPTIONAL_PAGE);
            } else {
                request.setAttribute(RequestParameter.FIND_USER_ERROR, true);
                logger.error("User with this id was not found.");
                router = new Router(PagePath.FIND_OPTIONAL_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during searching user with id = " + userId, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
