package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserStatus;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToUsersActivePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.USERS_PAGE_ACTIVE_REDIRECT);

        Integer currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.valueOf(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        UserService userService = new UserServiceImpl();
        try {
            List<User> users = userService.findByStatus(UserStatus.ACTIVE.name());
            request.setAttribute(RequestParameter.USERS, users);
            request = SendSplitParameters.sendSplitParametersUsers(request, users.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_ACTIVE);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_ACTIVE_USERS_PAGE_COMMAND);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to active users page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
