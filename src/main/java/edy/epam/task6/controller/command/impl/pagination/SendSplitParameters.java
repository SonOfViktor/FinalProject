package edy.epam.task6.controller.command.impl.pagination;

import edy.epam.task6.controller.command.PageSplitParameter;
import edy.epam.task6.controller.command.RequestParameter;
import jakarta.servlet.http.HttpServletRequest;

public class SendSplitParameters {

    public static HttpServletRequest sendSplitParametersUsers(HttpServletRequest request, int size) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_USERS_PER_PAGE);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_USERS_PER_PAGE));

        request.setAttribute(RequestParameter.NUMBER_OF_USERS, size);

        return request;
    }

    public static HttpServletRequest sendSplitParametersOrders(HttpServletRequest request, int size) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE));

        request.setAttribute(RequestParameter.NUMBER_OF_ORDERS, size);

        return request;
    }

    public static HttpServletRequest sendSplitParametersTattoos(HttpServletRequest request, int size) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE));

        request.setAttribute(RequestParameter.NUMBER_OF_TATTOOS, size);

        return request;
    }
}
