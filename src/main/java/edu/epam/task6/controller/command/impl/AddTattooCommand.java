package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.entity.User;
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

public class AddTattooCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        User userSession = (User)session.getAttribute(SessionAttribute.USER);
        request.setAttribute(RequestParameter.PROFILE, userSession);

        Long userId = (Long)session.getAttribute(SessionAttribute.USER_ID);
        int placeNumber = Integer.parseInt(request.getParameter(RequestParameter.TATTOO_PLACE));
        TattooService tattooService = TattooServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put(ColumnName.TATTOOS_NAME,
                    request.getParameter(RequestParameter.TATTOO_NAME));
            parameters.put(ColumnName.TATTOOS_DESCRIPTION,
                    request.getParameter(RequestParameter.TATTOO_DESCRIPTION));
            parameters.put(ColumnName.TATTOOS_PRICE,
                    request.getParameter(RequestParameter.TATTOO_PRICE));
            parameters.put(ColumnName.TATTOOS_WIDTH,
                    request.getParameter(RequestParameter.TATTOO_WIDTH));
            parameters.put(ColumnName.TATTOOS_HEIGHT,
                    request.getParameter(RequestParameter.TATTOO_HEIGHT));
            parameters.put(ColumnName.TATTOOS_IMAGE_URL,
                    request.getParameter(RequestParameter.TATTOO_IMAGE_URL));
            parameters.put(ColumnName.TATTOOS_STATUS,
                    request.getParameter(RequestParameter.TATTOO_STATUS));
            parameters.put(ColumnName.TATTOOS_PLACE, String.valueOf(placeNumber));
            parameters.put(ColumnName.TATTOOS_USER_ID, userId.toString());

            if (tattooService.addNewTattoo(parameters)) {
                logger.info("Tattoo has been added in catalog.");
                router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
            } else {
                logger.error("Error during adding tattoo.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }

        } catch (ServiceException e) {
            logger.error("Error during adding tattoo: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
