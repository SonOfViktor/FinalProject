package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.Command;
import edu.epam.task6.controller.command.PagePath;
import edu.epam.task6.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest req) {
        return new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE_500);
    }
}
