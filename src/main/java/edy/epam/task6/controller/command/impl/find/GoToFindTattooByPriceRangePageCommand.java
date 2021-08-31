package edy.epam.task6.controller.command.impl.find;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.entity.Tattoo;
import edy.epam.task6.model.entity.UserRole;
import edy.epam.task6.model.service.TattooService;
import edy.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class GoToFindTattooByPriceRangePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        TattooService tattooService = new TattooServiceImpl();
        BigDecimal minPrice =
                new BigDecimal(request.getParameter(RequestParameter.TATTOO_MIN_PRICE));
        BigDecimal maxPrice =
                new BigDecimal(request.getParameter(RequestParameter.TATTOO_MAX_PRICE));

        try {
            List<Tattoo> tattoos;
            if (userRole == UserRole.ADMIN) {
                tattoos = tattooService.findByPriceRange(minPrice, maxPrice);
            } else {
                tattoos = tattooService.findByPriceRangeAllActive(minPrice, maxPrice);
            }
            request.setAttribute(RequestParameter.CATALOG, tattoos);

            request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                    PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE);

            request.setAttribute(RequestParameter.PAGES_NUMBER,
                    (int)Math.ceil((double)tattoos.size()
                            / PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE));

            request.setAttribute(RequestParameter.NUMBER_OF_TATTOOS, tattoos.size());

            request.setAttribute(RequestParameter.TITLE_TATTOOS, RequestParameter.TITLE_TATTOOS_FOUNDED);
            router = new Router(PagePath.CATALOG_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during searching tattoos in price range = " + minPrice + " and " + maxPrice, e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
