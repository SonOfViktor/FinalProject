package edu.epam.task6.controller;

import edu.epam.task6.controller.command.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebServlet(name = "/ProjectServlet", value = "/ProjectServlet")
public class ProjectServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger();
    private final CommandProvider commandProvider = CommandProvider.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        Command command = commandProvider.getCommand(commandName);
        Router router = command.execute(request);
        switch (router.getType()) {
            case REDIRECT -> response.sendRedirect(router.getPage());
            case FORWARD -> {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(router.getPage());
                requestDispatcher.forward(request, response);
            }
            default -> {
                logger.error("Error in router type: ", router.getType());
                response.sendRedirect(PagePath.ERROR_PAGE_500);
            }
        }
    }

    @Override
    public void destroy() {}

}
