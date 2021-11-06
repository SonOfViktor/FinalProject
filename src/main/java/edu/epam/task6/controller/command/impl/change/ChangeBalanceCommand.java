package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeBalanceCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = UserServiceImpl.getInstance();
        try {
            Map<String, String> parameters = new HashMap<>();
            Optional<User> userBase = userService.findById(userId);
            if (userBase.isPresent()) {
                parameters.put(ColumnName.USER_BALANCE,
                        request.getParameter(RequestParameter.USER_BALANCE));
                router = isUpdateUserBalance(session, parameters, userId);
            } else {
                logger.error("Error during finding user by id from session: ");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during changing balance of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private Router isUpdateUserBalance(HttpSession session,
                                       Map<String, String> parameters,
                                       Long userId) throws ServiceException {
        Router router;
        UserService userService = UserServiceImpl.getInstance();
        if (userService.updateBalance(parameters, userId)) {
            Optional<User> userBase = userService.findById(userId);
            if (userBase.isPresent()) {
                session.setAttribute(SessionAttribute.USER, userBase.get());
                router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
                logger.info("Balance updated successfully.");
            } else {
                router = new Router(PagePath.ERROR_PAGE_500);
                logger.error("Error during finding user by id from session: ");
            }
        } else {
            router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
            logger.info("Failed to update balance, maximum exceeded.");
        }
        return router;
    }
}
