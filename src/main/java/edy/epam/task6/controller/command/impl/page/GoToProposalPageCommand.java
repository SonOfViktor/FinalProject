package edy.epam.task6.controller.command.impl.page;

import edy.epam.task6.controller.command.Command;
import edy.epam.task6.controller.command.PagePath;
import edy.epam.task6.controller.command.Router;
import jakarta.servlet.http.HttpServletRequest;

public class GoToProposalPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        router = new Router(PagePath.PROPOSAL_PAGE);
        return router;
    }
}
