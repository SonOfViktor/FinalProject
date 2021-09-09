package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER);
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.STATUS);
        session.setAttribute(SessionAttribute.ROLE, UserRole.VISITOR);
        session.setAttribute(SessionAttribute.AUTHENTICATION, false);
        Router router = new Router(Router.RouterType.REDIRECT, PagePath.LOGIN_PAGE_REDIRECT);
        return router;
    }
}
