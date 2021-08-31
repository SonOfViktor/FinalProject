package edy.epam.task6.controller.command.impl.find;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToFindUsersByNamePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService userService = new UserServiceImpl();
        String usersName = request.getParameter(RequestParameter.USER_NAME);
        try {
            List<User> users = userService.findByName(usersName);
            request.setAttribute(RequestParameter.USERS, users);

            request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                    PageSplitParameter.NUMBER_OF_USERS_PER_PAGE);

            request.setAttribute(RequestParameter.PAGES_NUMBER,
                    (int)Math.ceil((double)users.size()
                            / PageSplitParameter.NUMBER_OF_USERS_PER_PAGE));

            request.setAttribute(RequestParameter.NUMBER_OF_USERS, users.size());

            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_FOUNDED);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching users with name = " + usersName, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
