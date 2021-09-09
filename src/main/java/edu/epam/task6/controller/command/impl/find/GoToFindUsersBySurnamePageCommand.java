package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToFindUsersBySurnamePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService userService = new UserServiceImpl();
        String usersSurname = request.getParameter(RequestParameter.USER_SURNAME);
        try {
            List<User> users = userService.findBySurname(usersSurname);
            request.setAttribute(RequestParameter.USERS, users);

            request = SendSplitParameters.sendSplitParametersUsers(request, users.size());

            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_FOUNDED);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching users with surname = " + usersSurname, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
