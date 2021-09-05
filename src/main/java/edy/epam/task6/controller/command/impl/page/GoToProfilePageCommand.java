package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.*;
import edy.epam.task6.model.entity.User;
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
        User user = (User)session.getAttribute(SessionAttribute.USER);
        request.setAttribute(RequestParameter.PROFILE, user);
        switch (user.getRole()) {
            case ADMIN -> {
                router = new Router(PagePath.PROFILE_PAGE_ADMIN);
            }
            case USER -> {
                router = new Router(PagePath.PROFILE_PAGE_USER);
            }
            default -> {
                logger.error("User with this login and password was not found.");
                router = new Router(PagePath.LOGIN_PAGE);
            }
        }
        return router;
    }
}
