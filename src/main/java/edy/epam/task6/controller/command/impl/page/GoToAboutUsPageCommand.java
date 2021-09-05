package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.Router;
import edy.epam.task6.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToAboutUsPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ABOUT_US_PAGE_REDIRECT);

        router = new Router(PagePath.ABOUT_US_PAGE);
        return router;
    }
}
