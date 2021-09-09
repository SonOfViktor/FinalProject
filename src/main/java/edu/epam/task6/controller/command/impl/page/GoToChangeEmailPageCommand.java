package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToChangeEmailPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CHANGE_PAGE_EMAIL_REDIRECT);

        request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_EMAIL);
        router = new Router(PagePath.CHANGE_PAGE);
        return router;
    }
}
