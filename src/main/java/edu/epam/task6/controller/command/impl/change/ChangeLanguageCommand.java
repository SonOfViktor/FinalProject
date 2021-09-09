package edu.epam.task6.controller.command.impl.change;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.Router;
import edu.epam.task6.controller.command.SessionAttribute;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {

    private static final String ENGLISH = "en";
    private static final String RUSSIAN = "ru";

    @Override
    public Router execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String language = session.getAttribute(SessionAttribute.LOCALE).toString();
        if (language.equals(ENGLISH)) {
            session.setAttribute(SessionAttribute.LOCALE, RUSSIAN);
        } else {
            session.setAttribute(SessionAttribute.LOCALE, ENGLISH);
        }
        return new Router(Router.RouterType.REDIRECT,
                session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
    }
}
