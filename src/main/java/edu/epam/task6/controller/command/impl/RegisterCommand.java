package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import edu.epam.task6.util.EmailSender;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.util.RegisterCodeGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RegisterCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final String EMAIL_MESSAGE_TITLE = "Email confirmation";
    private static final String EMAIL_MESSAGE_TEXT = "Your registration confirmation code: ";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        try {
            String password = request.getParameter(RequestParameter.USER_PASSWORD);
            String repeatPassword = request.getParameter(RequestParameter.USER_REPEAT_PASSWORD);
            if (password.equals(repeatPassword)) {
                router = tryRegisterUser(request, session);
            } else {
                logger.error("Password and repeated password will not match.");
                request.setAttribute(RequestParameter.REPEAT_PASSWORD_ERROR, true);
                router = new Router(PagePath.REGISTER_PAGE);
            }
        } catch (ServiceException e) {
            logger.error("Error during register user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private Router tryRegisterUser(HttpServletRequest request, HttpSession session)
            throws ServiceException {
        Router router;
        Map<String, String> parameters = addParameters(request);
        UserService userService = UserServiceImpl.getInstance();
        Optional<User> user = userService.findByLogin(parameters.get(ColumnName.USER_LOGIN));
        if (!user.isPresent()) {
            router = registerUser(request, session, parameters);
        } else {
            logger.error("User with this login is already registered.");
            request.setAttribute(RequestParameter.REPEAT_LOGIN_ERROR, true);
            router = new Router(PagePath.REGISTER_PAGE);
        }
        return router;
    }

    private Map<String, String> addParameters(HttpServletRequest request) {
        String password = request.getParameter(RequestParameter.USER_PASSWORD);
        String repeatPassword = request.getParameter(RequestParameter.USER_REPEAT_PASSWORD);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(ColumnName.USER_EMAIL, request.getParameter(RequestParameter.USER_EMAIL));
        parameters.put(ColumnName.USER_LOGIN, request.getParameter(RequestParameter.USER_LOGIN));
        parameters.put(ColumnName.USER_PASSWORD, password);
        parameters.put(ColumnName.USER_REPEAT_PASSWORD, repeatPassword);
        parameters.put(ColumnName.USER_NAME, request.getParameter(RequestParameter.USER_NAME));
        parameters.put(ColumnName.USER_SURNAME, request.getParameter(RequestParameter.USER_SURNAME));
        parameters.put(ColumnName.USER_REGISTRATION_DATE,
                request.getParameter(RequestParameter.USER_REGISTRATION_DATE));
        return parameters;
    }

    private Router registerUser(HttpServletRequest request,
                                HttpSession session,
                                Map<String, String> parameters) throws ServiceException {
        Router router;
        UserService userService = UserServiceImpl.getInstance();
        String code = RegisterCodeGenerator.generateCode();
        parameters.put(ColumnName.USER_REGISTER_CODE, code);
        if (userService.registerUser(parameters)) {
            sendCodeToUserEmail(request, code);
            session.setAttribute(SessionAttribute.USER_LOGIN, parameters.get(ColumnName.USER_LOGIN));
            router = new Router(PagePath.CODE_PAGE);
            logger.info("User registered but not verified.");
        } else {
            router = new Router(PagePath.ERROR_PAGE_500);
            logger.error("Error during register user.");
        }
        return router;
    }

    private void sendCodeToUserEmail(HttpServletRequest request, String code) {
        EmailSender emailSender = new EmailSender(
                request.getParameter(RequestParameter.USER_EMAIL),
                EMAIL_MESSAGE_TITLE,
                EMAIL_MESSAGE_TEXT + code);
        emailSender.start();
    }
}
