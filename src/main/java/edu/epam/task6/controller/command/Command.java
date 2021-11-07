package edu.epam.task6.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     */
    Router execute(HttpServletRequest request);

    /**
     * Pages management determines the previous page and page number in pagination.
     *
     * @param request  the request
     * @param pagePath the page path to the previous page
     * @return the int current page in pagination
     */
    default int pagesManagement(HttpServletRequest request, String pagePath) {
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, pagePath);

        int currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }
        return currentPage;
    }
}
