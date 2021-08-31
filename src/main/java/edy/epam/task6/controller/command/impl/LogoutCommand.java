package edy.epam.task6.controller.command.impl;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.Router;
import edy.epam.task6.controller.command.SessionAttribute;
import edy.epam.task6.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER);
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.STATUS);
        session.setAttribute(SessionAttribute.ROLE, UserRole.VISITOR);
        session.setAttribute(SessionAttribute.AUTHENTICATION, false);
        Router router = new Router(PagePath.LOGIN_PAGE);
        return router;
    }
}
