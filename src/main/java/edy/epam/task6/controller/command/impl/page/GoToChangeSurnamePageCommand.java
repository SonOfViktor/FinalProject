package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.RequestParameter;
import edy.epam.task6.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToChangeSurnamePageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.USER_SURNAME);
        router = new Router(PagePath.CHANGE_PAGE);
        return router;
    }
}
