package edu.epam.task6.controller.command.impl.find;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class FindTattooByPriceRangeCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        int currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        TattooService tattooService = TattooServiceImpl.getInstance();
        if (request.getParameter(RequestParameter.TATTOO_MIN_PRICE) != null) {
            session.setAttribute(SessionAttribute.FIND_PARAMETER_ONE,
                    request.getParameter(RequestParameter.TATTOO_MIN_PRICE));
        }
        if (request.getParameter(RequestParameter.TATTOO_MAX_PRICE) != null) {
            session.setAttribute(SessionAttribute.FIND_PARAMETER_TWO,
                    request.getParameter(RequestParameter.TATTOO_MAX_PRICE));
        }
        String minPrice = session.getAttribute(SessionAttribute.FIND_PARAMETER_ONE).toString();
        String maxPrice = session.getAttribute(SessionAttribute.FIND_PARAMETER_TWO).toString();

        try {
            List<Tattoo> tattoos;
            if (userRole == UserRole.ADMIN) {
                tattoos = tattooService.findByPriceRange(minPrice, maxPrice);
            } else {
                tattoos = tattooService.findByPriceRangeAllActive(minPrice, maxPrice);
            }
            request.setAttribute(RequestParameter.CATALOG, tattoos);
            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            sendSplitParameters.sendSplitParametersTattoos(request, tattoos.size(), currentPage);
            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_FOUNDED);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_FIND_TATTOO_BY_PRICE_RANGE_PAGE);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching tattoos in price range = " + minPrice + " and " + maxPrice, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
