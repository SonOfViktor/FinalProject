package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserStatus;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import edu.epam.task6.exception.ServiceException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        try {
            String userLogin = session.getAttribute(SessionAttribute.USER_LOGIN).toString();
            Optional<User> user = userService.findByLogin(userLogin);
            if (user.isPresent()) {
                User localUser = user.get();
                router = checkCode(request, localUser);
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
    
    private Router checkCode(HttpServletRequest request, User localUser)
            throws ServiceException {
        Router router;
        UserService userService = UserServiceImpl.getInstance();
        String generateCode = localUser.getRegisterCode().toString();
        String enteredCode = request.getParameter(RequestParameter.REGISTER_CODE);
        if (generateCode.equals(enteredCode)) {
            Long userId = localUser.getUserId();
            Map<String, String> parameters = new HashMap<>();
            parameters.put(ColumnName.USER_STATUS, UserStatus.ACTIVE.toString());
            userService.updateStatus(parameters, userId);
            router = new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE_REDIRECT);
            logger.error("User registration was successful.");
        } else {
            request.setAttribute(RequestParameter.ENTERED_CODE_ERROR, true);
            router = new Router(PagePath.CODE_PAGE);
            logger.error("Invalid code entered.");
        }
        return router;
    }
}
