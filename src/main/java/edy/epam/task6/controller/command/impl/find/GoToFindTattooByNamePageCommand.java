package edy.epam.task6.controller.command.impl.find;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.entity.UserRole;
import edy.epam.task6.model.service.TattooService;
import edy.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class GoToFindTattooByNamePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        TattooService tattooService = new TattooServiceImpl();
        String tattooName = request.getParameter(RequestParameter.TATTOO_NAME);
        try {
            List<Tattoo> tattoos;
            if (userRole == UserRole.ADMIN) {
                tattoos = tattooService.findByName(tattooName);
            } else {
                tattoos = tattooService.findByNameAllActive(tattooName);
            }
            request.setAttribute(RequestParameter.CATALOG, tattoos);

            request = SendSplitParameters.sendSplitParametersTattoos(request, tattoos.size());

            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_FOUNDED);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching tattoos with name = " + tattooName, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
