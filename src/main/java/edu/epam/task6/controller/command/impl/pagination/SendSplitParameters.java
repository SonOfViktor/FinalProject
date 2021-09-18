package edu.epam.task6.controller.command.impl.pagination;

import edu.epam.task6.controller.command.PageSplitParameter;
import edu.epam.task6.controller.command.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

public class SendSplitParameters {

    public static HttpServletRequest sendSplitParametersUsers(HttpServletRequest request, int size, int currentPage) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_USERS_PER_PAGE);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_USERS_PER_PAGE));
        return request;
    }

    public static HttpServletRequest sendSplitParametersOrders(HttpServletRequest request, int size, int currentPage) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE));
        return request;
    }

    public static HttpServletRequest sendSplitParametersTattoos(HttpServletRequest request, int size, int currentPage) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE));
        return request;
    }

    public static HttpServletRequest sendSplitParametersComments(HttpServletRequest request,
                                                                 int size,
                                                                 int currentPage,
                                                                 int numberOfElementsPerPage) {

        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE, numberOfElementsPerPage);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE));
        return request;
    }
}
