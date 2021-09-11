package edu.epam.task6.controller.filter;

import edu.epam.task6.controller.command.RequestParameter;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebFilter( urlPatterns = { "/ProjectServlet" })
public class HistoryFilter implements Filter {

    private static final String PROFILE_COMMAND = "to_profile_page_command";

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (request.getParameter(RequestParameter.COMMAND).equals(PROFILE_COMMAND)) {
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
            response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
            response.setDateHeader("Expires", 0); // Proxies.
        }
        filterChain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }
}
