package edy.epam.task6.controller.command.impl.find;

import edy.epam.task6.controller.command.*;
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

import java.util.Optional;

public class GoToFindTattooByIdPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        TattooService tattooService = new TattooServiceImpl();
        Long tattooId = Long.valueOf(request.getParameter(RequestParameter.TATTOO_ID));
        try {
            Optional<Tattoo> tattoo;
            if (userRole == UserRole.ADMIN) {
                tattoo = tattooService.findById(tattooId);
            } else {
                tattoo = tattooService.findByIdAllActive(tattooId);
            }
            if (tattoo.isPresent()) {
                Tattoo localTattoo = tattoo.get();
                request.setAttribute(RequestParameter.FIND_TATTOO_ERROR, false);
                request.setAttribute(RequestParameter.TATTOO, localTattoo);
                router = new Router(PagePath.FIND_OPTIONAL_PAGE);
            } else {
                request.setAttribute(RequestParameter.FIND_TATTOO_ERROR, true);
                logger.error("Tattoo with this id was not found. id = " + tattooId);
                router = new Router(PagePath.FIND_OPTIONAL_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during searching tattoo with id = " + tattooId, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
