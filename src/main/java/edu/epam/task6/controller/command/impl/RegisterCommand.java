package edu.epam.task6.controller.command.impl;

import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import edu.epam.task6.util.EmailSender;
import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.util.RegisterCodeGenerator;
import jakarta.servlet.http.HttpServletRequest;
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
        UserService userService = UserServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            String password = request.getParameter(RequestParameter.USER_PASSWORD);
            String repeatPassword = request.getParameter(RequestParameter.USER_REPEAT_PASSWORD);
            if (password.equals(repeatPassword)) {
                parameters.put(ColumnName.USER_EMAIL, request.getParameter(RequestParameter.USER_EMAIL));
                parameters.put(ColumnName.USER_LOGIN, request.getParameter(RequestParameter.USER_LOGIN));
                parameters.put(ColumnName.USER_PASSWORD, password);
                parameters.put(ColumnName.USER_REPEAT_PASSWORD, repeatPassword);
                parameters.put(ColumnName.USER_NAME, request.getParameter(RequestParameter.USER_NAME));
                parameters.put(ColumnName.USER_SURNAME, request.getParameter(RequestParameter.USER_SURNAME));
                parameters.put(ColumnName.USER_REGISTRATION_DATE,
                        request.getParameter(RequestParameter.USER_REGISTRATION_DATE));

                Optional<User> user = userService.findByLogin(parameters.get(ColumnName.USER_LOGIN));
                if (!user.isPresent()) {
                    String code = RegisterCodeGenerator.generateCode();
                    parameters.put(ColumnName.USER_REGISTER_CODE, code);
                    if (userService.registerUser(parameters)) {
                        EmailSender emailSender = new EmailSender(
                                request.getParameter(RequestParameter.USER_EMAIL),
                                EMAIL_MESSAGE_TITLE,
                                EMAIL_MESSAGE_TEXT + code);
                        emailSender.start();
                        request.setAttribute(RequestParameter.GENERATE_CODE, code);
                        request.setAttribute(RequestParameter.USER_LOGIN, parameters.get(ColumnName.USER_LOGIN));
                        router = new Router(PagePath.CODE_PAGE);
                        logger.info("User registered but not verified.");
                    } else {
                        logger.error("Error during register user.");
                        router = new Router(PagePath.ERROR_PAGE_500);
                    }
                } else {
                    logger.error("User with this login is already registered.");
                    router = new Router(Router.RouterType.REDIRECT, PagePath.MAIN_PAGE_REDIRECT);
                }
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

}
