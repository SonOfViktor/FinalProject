package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class GoToLoginPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.LOGIN_PAGE_REDIRECT);
        router = new Router(PagePath.LOGIN_PAGE);
        return router;
    }
}
