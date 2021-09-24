package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import edu.epam.task6.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToHomePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    private final static String ENGLISH = "en";
    private final static String RUSSIAN = "ru";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.MAIN_PAGE_REDIRECT);
        if (session.getAttribute(SessionAttribute.LOCALE) == null) {
            session.setAttribute(SessionAttribute.LOCALE, RUSSIAN);
        }
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE, PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE);
        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, PageSplitParameter.FIRST_PAGE);
        request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_HOME);
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.MAIN_PAGE_REDIRECT);
        TattooService catalogService = TattooServiceImpl.getInstance();
        try {
            List<Tattoo> catalogElements = catalogService.findAllActive();
            request.setAttribute(RequestParameter.CATALOG, catalogElements);
            router = new Router(PagePath.MAIN_PAGE);
        } catch (ServiceException e) {
            logger.error("Error loading pre-catalog on homepage.");
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
