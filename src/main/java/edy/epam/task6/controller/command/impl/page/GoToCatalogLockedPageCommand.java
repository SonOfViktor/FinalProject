package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.entity.TattooStatus;
import edy.epam.task6.model.service.TattooService;
import edy.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class GoToCatalogLockedPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        TattooService catalogService = new TattooServiceImpl();
        try {
            List<Tattoo> catalogElements = catalogService.findByStatus(TattooStatus.LOCKED.name());
            request.setAttribute(RequestParameter.CATALOG, catalogElements);

            request = SendSplitParameters.sendSplitParametersTattoos(request, catalogElements.size());

            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_LOCKED);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e){
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
