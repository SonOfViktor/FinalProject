package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ProposeTattooCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String PRICE = "0";
    private static final String STATUS = "3";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        int placeNumber = Integer.valueOf(request.getParameter(RequestParameter.TATTOO_PLACE));
        HttpSession session = request.getSession();
        Long userId = (Long)session.getAttribute(SessionAttribute.USER_ID);
        TattooService tattooService = TattooServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put(ColumnName.TATTOOS_NAME,
                    request.getParameter(RequestParameter.TATTOO_NAME));
            parameters.put(ColumnName.TATTOOS_DESCRIPTION,
                    request.getParameter(RequestParameter.TATTOO_DESCRIPTION));
            parameters.put(ColumnName.TATTOOS_PRICE, PRICE);
            parameters.put(ColumnName.TATTOOS_WIDTH,
                    request.getParameter(RequestParameter.TATTOO_WIDTH));
            parameters.put(ColumnName.TATTOOS_HEIGHT,
                    request.getParameter(RequestParameter.TATTOO_HEIGHT));
            parameters.put(ColumnName.TATTOOS_IMAGE_URL,
                    request.getParameter(RequestParameter.TATTOO_IMAGE_URL));
            parameters.put(ColumnName.TATTOOS_STATUS, STATUS);
            parameters.put(ColumnName.TATTOOS_PLACE, String.valueOf(placeNumber));
            parameters.put(ColumnName.TATTOOS_USER_ID, userId.toString());
            boolean added = tattooService.AddNewTattoo(parameters);
            if (added) {
                logger.info("Tattoo has been added for review of the administration.");
                router = new Router(PagePath.MAIN_PAGE);
            } else {
                logger.error("Error during proposing tattoo.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }

        } catch (ServiceException e) {
            logger.error("Error during proposing tattoo: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
