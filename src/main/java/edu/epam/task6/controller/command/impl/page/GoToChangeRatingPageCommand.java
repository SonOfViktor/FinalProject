package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;

public class GoToChangeRatingPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        Long orderId = Long.valueOf(request.getParameter(RequestParameter.ORDER_ID));
        request.setAttribute(RequestParameter.ORDER_ID, orderId);
        request.setAttribute(RequestParameter.WHAT_CHANGE, RequestParameter.RATING);
        router = new Router(PagePath.CHANGE_PAGE);
        return router;
    }
}
