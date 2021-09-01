package edy.epam.task6.controller.command.impl.change;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.exception.ServiceException;
import edy.epam.task6.model.dao.ColumnName;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.service.UserService;
import edy.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChangeSurnameCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(SessionAttribute.USER);
        Long userId = user.getUserId();
        UserService userService = new UserServiceImpl();
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put(ColumnName.USER_SURNAME,
                    request.getParameter(RequestParameter.USER_SURNAME));
            userService.updateSurname(parameters, userId);
            Optional<User> resultUser = userService.findById(userId);
            request.setAttribute(RequestParameter.PROFILE, resultUser.get());
            switch (user.getRole()) {
                case ADMIN -> {
                    router = new Router(PagePath.PROFILE_PAGE_ADMIN);
                }
                case USER -> {
                    router = new Router(PagePath.PROFILE_PAGE_USER);
                }
                default -> {
                    logger.error("Error after change surname with a return to the profile page.");
                    router = new Router(PagePath.ERROR_PAGE_500);
                }
            }
        } catch (ServiceException e) {
            logger.error("Error during changing surname of user: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
