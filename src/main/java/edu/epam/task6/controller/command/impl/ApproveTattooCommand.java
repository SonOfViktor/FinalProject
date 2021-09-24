package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.TattooService;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.TattooServiceImpl;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ApproveTattooCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        User userSession = (User)session.getAttribute(SessionAttribute.USER);
        request.setAttribute(RequestParameter.PROFILE, userSession);

        TattooService tattooService = TattooServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            Long tattooId = Long.valueOf(request.getParameter(RequestParameter.TATTOO_ID));
            BigDecimal tattooPrice = BigDecimal.valueOf(
                    Long.valueOf(request.getParameter(RequestParameter.TATTOO_PRICE)));
            String discount = request.getParameter(RequestParameter.USER_DISCOUNT);
            boolean button = Boolean.parseBoolean(
                    request.getParameter(RequestParameter.TATTOO_BUTTON));
            Optional<Tattoo> tattoo = tattooService.findById(tattooId);
            if (tattoo.isPresent()) {
                TattooStatus tattooStatus = tattoo.get().getStatus();
                if (tattooStatus == TattooStatus.OFFERED_BY_USER && button == true) {
                    tattooStatus = TattooStatus.ACTIVE;
                } else if (tattooStatus == TattooStatus.OFFERED_BY_USER && !button) {
                    tattooStatus = TattooStatus.LOCKED;
                }

                Long userId = tattoo.get().getUserId();
                parameters.put(ColumnName.USER_DISCOUNT, discount);

                parameters.put(ColumnName.TATTOOS_STATUS, tattooStatus.toString());
                parameters.put(ColumnName.TATTOOS_PRICE, tattooPrice.toString());
                if (tattooService.updateStatus(parameters, tattooId)
                        && tattooService.updatePrice(parameters, tattooId)
                        && userService.updateDiscount(parameters, userId)) {
                    logger.info("Tattoo approving was successful.");
                    router = new Router(Router.RouterType.REDIRECT,
                            session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
                } else {
                    logger.error("An error in approving tattoo.");
                    router = new Router(PagePath.ERROR_PAGE_500);
                }
            } else {
                logger.error("Tattoo with this id was not found.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during approving of tattoo: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
