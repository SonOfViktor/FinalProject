package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import static edy.epam.task6.controller.command.CommandType.TO_ALL_USERS_PAGE_COMMAND;

public class GoToUsersAllPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.USERS_PAGE_ALL_REDIRECT);

        UserService userService = new UserServiceImpl();
        try {
            List<User> users = userService.findAll();
            request.setAttribute(RequestParameter.USERS, users);

            request = SendSplitParameters.sendSplitParametersUsers(request, users.size());

            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_ALL);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
