package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeTattooStatusCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        TattooService tattooService = TattooServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            Long tattooId = Long.valueOf(request.getParameter(RequestParameter.TATTOO_ID));
            boolean button = Boolean.parseBoolean(
                    request.getParameter(RequestParameter.TATTOO_BUTTON));
            Optional<Tattoo> tattoo = tattooService.findById(tattooId);
            if (tattoo.isPresent()) {
                TattooStatus tattooStatus = tattoo.get().getStatus();
                if (tattooStatus == TattooStatus.ACTIVE ||
                        tattooStatus == TattooStatus.OFFERED_BY_USER && !button) {
                    tattooStatus = TattooStatus.LOCKED;
                } else if (tattooStatus == TattooStatus.LOCKED ||
                        tattooStatus == TattooStatus.OFFERED_BY_USER && button) {
                    tattooStatus = TattooStatus.ACTIVE;
                }
                parameters.put(ColumnName.TATTOOS_STATUS, tattooStatus.toString());
                if (tattooService.updateStatus(parameters, tattooId)) {
                    logger.info("Tattoo status change was successful.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                } else {
                    logger.error("An error in changing the tattoo's status.");
                    router = new Router(PagePath.ERROR_PAGE_500);
                }
            } else {
                logger.error("Tattoo with this id was not found.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing status of tattoo: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
