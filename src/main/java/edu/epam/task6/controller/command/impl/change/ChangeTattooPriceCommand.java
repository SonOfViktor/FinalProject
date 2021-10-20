package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeTattooPriceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        TattooService tattooService = TattooServiceImpl.getInstance();
        try {
            Long tattooId = Long.valueOf(request.getParameter(RequestParameter.TATTOO_ID));
            BigDecimal tattooPrice = BigDecimal.valueOf(
                    Long.parseLong(request.getParameter(RequestParameter.TATTOO_PRICE)));
            Optional<Tattoo> tattoo = tattooService.findById(tattooId);
            if (tattoo.isPresent()) {
                Map<String, String> parameters = new HashMap<>();
                parameters.put(ColumnName.TATTOOS_PRICE, tattooPrice.toString());
                if (tattooService.updatePrice(parameters, tattooId)) {
                    logger.info("Tattoo price update was successful.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                } else {
                    logger.error("An error in updating tattoo price.");
                    router = new Router(PagePath.ERROR_PAGE_500);
                }
            } else {
                logger.error("Tattoo with this id was not found.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing tattoo price: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
