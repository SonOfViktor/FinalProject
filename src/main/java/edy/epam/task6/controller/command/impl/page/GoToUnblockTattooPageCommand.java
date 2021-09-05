package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToUnblockTattooPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CHANGE_UNBLOCK_TATTOO_PAGE_REDIRECT);

        request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.TATTOO_STATUS);
        request.setAttribute(RequestParameter.TATTOO_BUTTON, true);
        router = new Router(PagePath.CHANGE_PAGE);
        return router;
    }
}
