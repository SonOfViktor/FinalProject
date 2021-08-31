package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.entity.UserStatus;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class GoToUsersBlockedPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.USERS_PAGE_BLOCKED_REDIRECT);

        UserService userService = new UserServiceImpl();
        try {
            List<User> users = userService.findByStatus(UserStatus.BLOCKED.name());
            request.setAttribute(RequestParameter.USERS, users);

            request = SendSplitParameters.sendSplitParametersUsers(request, users.size());

            request.setAttribute(RequestParameter.TITLE_USERS, RequestParameter.TITLE_USERS_BLOCKED);
            router = new Router(PagePath.USERS_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
