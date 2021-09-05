package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToChangeUserStatusPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CHANGE_PAGE_USER_STATUS_REDIRECT);

        request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_STATUS);
        router = new Router(PagePath.CHANGE_PAGE);
        return router;
    }
}
