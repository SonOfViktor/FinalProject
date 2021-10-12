package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class LogoutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(SessionAttribute.USER);
        session.removeAttribute(SessionAttribute.USER_ID);
        session.removeAttribute(SessionAttribute.USER_LOGIN);
        session.removeAttribute(SessionAttribute.STATUS);
        session.setAttribute(SessionAttribute.ROLE, UserRole.VISITOR);
        return new Router(Router.RouterType.REDIRECT, PagePath.LOGIN_PAGE_REDIRECT);
    }
}
