package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.Router;
import edy.epam.task6.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToCreateOrderPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CREATE_ORDER_PAGE_REDIRECT);

        router = new Router(PagePath.CREATE_ORDER_PAGE);
        return router;
    }
}
