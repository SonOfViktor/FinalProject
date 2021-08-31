package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.RequestParameter;
import edy.epam.task6.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToApproveTattooPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Long tattooId = Long.valueOf(request.getParameter(RequestParameter.TATTOO_ID));
        request.setAttribute(RequestParameter.TATTOO_ID, tattooId);
        router = new Router(PagePath.APPROVE_TATTOO_PAGE);
        return router;
    }
}
