package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.controller.command.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GoToProfilePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.PROFILE_PAGE_REDIRECT);
        if (session.getAttribute(SessionAttribute.USER) != null) {
            User user = (User)session.getAttribute(SessionAttribute.USER);
            request.setAttribute(RequestParameter.USERS, user);
            router = switch (user.getRole()) {
                case ADMIN -> new Router(PagePath.PROFILE_PAGE_ADMIN);
                case USER -> new Router(PagePath.PROFILE_PAGE_USER);
                default -> new Router(PagePath.LOGIN_PAGE);
            };
        } else {
            logger.error("The session does not contain a user. An attempt to get to the profile page without authorization is possible.");
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
