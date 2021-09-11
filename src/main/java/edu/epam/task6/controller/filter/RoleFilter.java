package edu.epam.task6.controller.filter;

import edu.epam.task6.controller.command.CommandType;
import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter( urlPatterns = { "/ProjectServlet" },
        initParams = { @WebInitParam(name = "INDEX_PATH", value = "index.jsp") })
public class RoleFilter implements Filter {
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) {
        indexPath = filterConfig.getInitParameter("INDEX_PATH");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String command = request.getParameter(RequestParameter.COMMAND);
        UserRole role;
        if (session.getAttribute(SessionAttribute.USER) == null) {
            role = UserRole.VISITOR;
            session.setAttribute(SessionAttribute.ROLE, role);
        } else if (session.getAttribute(SessionAttribute.ROLE) == null) {
            User user = (User) session.getAttribute(SessionAttribute.USER);
            role = user.getRole();
            session.setAttribute(SessionAttribute.ROLE, role);
        }

        role = (UserRole) session.getAttribute(SessionAttribute.ROLE);

        if (role.equals(UserRole.VISITOR)) {
            if (!CommandType.valueOf(command.toUpperCase()).isContainRole(UserRole.VISITOR)) {
                System.out.println("Work this filter");
                response.sendRedirect(request.getContextPath() + indexPath);
                return;
            }
        }

        if (!CommandType.valueOf(command.toUpperCase()).isContainRole(role) && !role.equals(UserRole.VISITOR)) {
            response.sendError(403);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {}
}
