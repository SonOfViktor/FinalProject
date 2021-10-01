package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class FindUserByIdCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService userService = UserServiceImpl.getInstance();
        Long userId = Long.valueOf(request.getParameter(RequestParameter.USER_ID));
        try {
            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                User localUser = user.get();
                request.setAttribute(RequestParameter.FIND_USER_ERROR, false);
                request.setAttribute(RequestParameter.USER, localUser);
            } else {
                request.setAttribute(RequestParameter.FIND_USER_ERROR, true);
                logger.error("User with this id was not found.");
            }
            router = new Router(PagePath.FIND_OPTIONAL_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching user with id = " + userId, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
