package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Comment;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.service.CommentService;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.CommentServiceImpl;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class AboutUsPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger();
    private static final Long ADMIN_ID = 1L;

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        UserRole sessionRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ABOUT_US_PAGE_REDIRECT);

        int currentPage = 1;
        if (request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER) != null) {
            currentPage = Integer.parseInt(request.getParameter(RequestParameter.CURRENT_PAGE_NUMBER));
        }

        CommentService commentService = CommentServiceImpl.getInstance();
        UserService userService = UserServiceImpl.getInstance();
        try {
            List<Comment> comments;
            comments = commentService.findAll();
            request.setAttribute(RequestParameter.COMMENTS, comments);

            SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
            Optional<User> user = userService.findById(ADMIN_ID);
            if (user.isPresent() && user.get().getRole().equals(UserRole.ADMIN) && sessionRole.equals(UserRole.ADMIN)) {
                Double averageRating = user.get().getAverageRating();
                request.setAttribute(RequestParameter.RATING, averageRating);
                sendSplitParameters.sendSplitParametersComments(
                        request,
                        comments.size(),
                        currentPage,
                        PageSplitParameter.NUMBER_OF_COMMENTS_PER_PAGE_ADMIN);
            } else if (user.isPresent() && user.get().getRole().equals(UserRole.ADMIN) && !sessionRole.equals(UserRole.ADMIN)) {
                Double averageRating = user.get().getAverageRating();
                request.setAttribute(RequestParameter.RATING, averageRating);
                sendSplitParameters.sendSplitParametersComments(
                        request,
                        comments.size(),
                        currentPage,
                        PageSplitParameter.NUMBER_OF_COMMENTS_PER_PAGE_USER);
            } else {
                Double averageRating = 0D;
                request.setAttribute(RequestParameter.RATING, averageRating);
                sendSplitParameters.sendSplitParametersComments(
                        request,
                        comments.size(),
                        currentPage,
                        PageSplitParameter.NUMBER_OF_COMMENTS_PER_PAGE_USER);
            }
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_ABOUT_US_PAGE);
            router = new Router(PagePath.ABOUT_US_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to about us page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}