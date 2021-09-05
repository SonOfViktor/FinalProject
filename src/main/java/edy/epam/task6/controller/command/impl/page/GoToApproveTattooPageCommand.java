package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
