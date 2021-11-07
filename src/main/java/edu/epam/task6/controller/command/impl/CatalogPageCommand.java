package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CatalogPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        int currentPage = pagesManagement(request, PagePath.CATALOG_PAGE_REDIRECT);
        TattooService catalogService = TattooServiceImpl.getInstance();
        try {
            List<Tattoo> catalogElements = catalogService.findAllActive();
            request.setAttribute(RequestParameter.CATALOG, catalogElements);
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersTattoos(request, catalogElements.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_ACTIVE);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_CATALOG_PAGE);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to catalog tattoos page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
