package edy.epam.task6.controller.filter;

import edy.epam.task6.controller.command.SessionAttribute;
import edy.epam.task6.model.entity.User;
import edy.epam.task6.model.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/ProjectServlet"})
public class RoleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        UserRole role;

        if (session.getAttribute(SessionAttribute.USER) == null) {
            role = UserRole.VISITOR;
            session.setAttribute(SessionAttribute.ROLE, role);
        } else if (session.getAttribute(SessionAttribute.ROLE) == null) {
            User user = (User) session.getAttribute(SessionAttribute.USER);
            role = user.getRole();
            session.setAttribute(SessionAttribute.ROLE, role);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
