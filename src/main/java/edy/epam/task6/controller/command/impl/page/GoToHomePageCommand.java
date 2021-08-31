package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.service.TattooService;
import edy.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class GoToHomePageCommand implements Command {

    private final static String ENGLISH = "en";
    private final static String RUSSIAN = "ru";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.LOCALE, RUSSIAN);
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.MAIN_PAGE_REDIRECT);
        TattooService catalogService = new TattooServiceImpl();
        try {
            List<Tattoo> catalogElements = catalogService.findNumberActive(PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE);
            request.setAttribute(RequestParameter.CATALOG, catalogElements);
            router = new Router(PagePath.MAIN_PAGE);
        } catch (ServiceException e) {
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
