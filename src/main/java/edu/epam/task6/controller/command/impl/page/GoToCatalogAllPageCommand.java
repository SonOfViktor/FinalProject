package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public class GoToCatalogAllPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.CATALOG_PAGE_ALL_REDIRECT);

        TattooService catalogService = new TattooServiceImpl();
        try {
            List<Tattoo> catalogElements = catalogService.findAll();
            request.setAttribute(RequestParameter.CATALOG, catalogElements);

            request = SendSplitParameters.sendSplitParametersTattoos(request, catalogElements.size());

            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_ALL);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
