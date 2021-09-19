package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.util.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToCatalogLockedPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CATALOG_PAGE_LOCKED_REDIRECT);

        Integer currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.valueOf(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        TattooService catalogService = new TattooServiceImpl();
        try {
            List<Tattoo> catalogElements = catalogService.findByStatus(TattooStatus.LOCKED.name());
            request.setAttribute(RequestParameter.CATALOG, catalogElements);
            request = SendSplitParameters.sendSplitParametersTattoos(request, catalogElements.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_LOCKED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_LOCKED_CATALOG_PAGE_COMMAND);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to locked tattoos page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
