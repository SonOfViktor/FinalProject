package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindUsersByNameCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();

        int currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        UserService userService = UserServiceImpl.getInstance();
        if (request.getParameter(RequestParameter.USER_NAME) != null) {
            session.setAttribute(SessionAttribute.FIND_PARAMETER_ONE,
                    request.getParameter(RequestParameter.USER_NAME));
        }
        String usersName = session.getAttribute(SessionAttribute.FIND_PARAMETER_ONE).toString();
        try {
            List<User> users = userService.findByName(usersName);
            request.setAttribute(RequestParameter.USERS, users);
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersUsers(request, users.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_FOUNDED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_FIND_USERS_BY_NAME_PAGE);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching users with name = " + usersName, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
