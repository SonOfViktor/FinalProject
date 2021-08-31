package edy.epam.task6.controller.command.impl;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest req) {
        return new Router(Router.RouterType.REDIRECT, PagePath.ERROR_PAGE);
    }
}
