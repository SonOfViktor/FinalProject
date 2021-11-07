package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserStatus;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import edu.epam.task6.util.EmailSender;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL_MESSAGE_TITLE = "Email confirmation";
    private static final String EMAIL_MESSAGE_TEXT = "Your registration confirmation code: ";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put(ColumnName.USER_LOGIN, request.getParameter(RequestParameter.USER_LOGIN));
            parameters.put(ColumnName.USER_PASSWORD, request.getParameter(RequestParameter.USER_PASSWORD));
            Optional<User> user =
                    userService.findByLoginAndPassword(parameters.get(ColumnName.USER_LOGIN),
                                                       parameters.get(ColumnName.USER_PASSWORD));
            if (user.isPresent()) {
                User localUser = user.get();
                router = login(request, session, localUser, parameters);
            } else {
                request.setAttribute(RequestParameter.LOGIN_ERROR, true);
                router = new Router(PagePath.LOGIN_PAGE);
                logger.error("User with this login and password was not found.");
            }
        } catch (ServiceException e) {
            router = new Router(PagePath.ERROR_PAGE_500);
            logger.error("Error during login user: ", e);
        }
        return router;
    }

    private Router login(HttpServletRequest request,
                         HttpSession session,
                         User localUser,
                         Map<String, String> parameters) {
        Router router;
        if (localUser.getStatus().equals(UserStatus.ACTIVE)) {
            setAttributes(request, session, localUser);
            router = new Router(Router.RouterType.REDIRECT, PagePath.PROFILE_PAGE_REDIRECT);
        } else if (localUser.getStatus().equals(UserStatus.NOT_CONFIRMED)) {
            session.setAttribute(SessionAttribute.USER_LOGIN, localUser.getLogin());
            sendRegistrationCode(localUser);
            request.setAttribute(RequestParameter.GENERATE_CODE, localUser.getRegisterCode());
            request.setAttribute(RequestParameter.USER_LOGIN, parameters.get(ColumnName.USER_LOGIN));
            router = new Router(PagePath.CODE_PAGE);
        } else {
            request.setAttribute(RequestParameter.USER_BLOCKED_ERROR, true);
            router = new Router(PagePath.LOGIN_PAGE);
            logger.info("This user is blocked.");
        }
        return router;
    }

    private void setAttributes(HttpServletRequest request, HttpSession session, User localUser) {
        request.setAttribute(RequestParameter.PROFILE, localUser);
        session.setAttribute(SessionAttribute.USER, localUser);
        session.setAttribute(SessionAttribute.USER_ID, localUser.getUserId());
        session.setAttribute(SessionAttribute.USER_LOGIN, localUser.getLogin());
        session.setAttribute(SessionAttribute.ROLE, localUser.getRole());
        session.setAttribute(SessionAttribute.STATUS, localUser.getStatus());
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.PROFILE_PAGE_REDIRECT);
    }

    private void sendRegistrationCode(User localUser) {
        EmailSender emailSender = new EmailSender(
                localUser.getEmail(),
                EMAIL_MESSAGE_TITLE,
                EMAIL_MESSAGE_TEXT + localUser.getRegisterCode().toString());
        emailSender.start();
    }
}
