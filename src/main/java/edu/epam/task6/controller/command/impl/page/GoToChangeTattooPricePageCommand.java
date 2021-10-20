package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;

public class GoToChangeTattooPricePageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Long tattooId = Long.valueOf(request.getParameter(RequestParameter.TATTOO_ID));
        request.setAttribute(RequestParameter.TATTOO_ID, tattooId);
        request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.TATTOO_PRICE);
        router = new Router(PagePath.CHANGE_PAGE);
        return router;
    }
}
