package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserStatus;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UsersBlockedPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int currentPage = pagesManagement(request, PagePath.USERS_PAGE_BLOCKED_REDIRECT);
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<User> users = userService.findByStatus(UserStatus.BLOCKED.name());
            request.setAttribute(RequestParameter.USERS, users);
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersUsers(request, users.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_BLOCKED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_BLOCKED_USERS_PAGE);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to blocked users page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
