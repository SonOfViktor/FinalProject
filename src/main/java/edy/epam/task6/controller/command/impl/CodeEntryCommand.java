package edy.epam.task6.controller.command.impl;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.ColumnName;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.entity.UserStatus;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CodeEntryCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        UserService userService = new UserServiceImpl();
        try {
            String enteredCode = request.getParameter(RequestParameter.REGISTER_CODE);
            String generateCode = request.getParameter(RequestParameter.GENERATE_CODE);
            String userLogin = request.getParameter(RequestParameter.USER_LOGIN);
            Optional<User> user = userService.findByLogin(userLogin);

            if (user.isPresent()) {
                if (generateCode.equals(enteredCode)) {
                    Long userId = user.get().getUserId();
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put(ColumnName.USER_STATUS, UserStatus.ACTIVE.toString());
                    userService.updateStatus(parameters, userId);
                    logger.error("User registration was successful.");
                    router = new Router(PagePath.MAIN_PAGE);
                } else {
                    logger.error("Invalid code entered.");
                    request.setAttribute(RequestParameter.ENTERED_CODE_ERROR, true);
                    router = new Router(PagePath.CODE_PAGE);
                }
            } else {
                logger.error("Error during entering code: user was not found.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during entering code: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
