package edu.epam.task6.controller.command;

import jakarta.servlet.http.HttpServletRequest;

public final class SendSplitParameters {

    private static SendSplitParameters instance;

    private SendSplitParameters(){}

    public static SendSplitParameters getInstance() {
        if (instance == null) {
            instance = new SendSplitParameters();
        }
        return instance;
    }

    public void sendSplitParametersUsers(HttpServletRequest request, int size, int currentPage) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_USERS_PER_PAGE);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_USERS_PER_PAGE));
    }

    public void sendSplitParametersOrders(HttpServletRequest request, int size, int currentPage) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_ORDERS_PER_PAGE));
    }

    public void sendSplitParametersTattoos(HttpServletRequest request, int size, int currentPage) {
        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE,
                PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size
                        / PageSplitParameter.NUMBER_OF_TATTOOS_PER_PAGE));
    }

    public void sendSplitParametersComments(HttpServletRequest request,
                                                                 int size,
                                                                 int currentPage,
                                                                 int numberOfElementsPerPage) {

        request.setAttribute(RequestParameter.ELEMENTS_PER_PAGE, numberOfElementsPerPage);

        request.setAttribute(RequestParameter.CURRENT_PAGE_NUMBER, currentPage);

        request.setAttribute(RequestParameter.PAGES_NUMBER,
                (int)Math.ceil((double)size / numberOfElementsPerPage));
    }
}
