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
        int currentPage = pagesManagement(request, PagePath.ABOUT_US_PAGE_REDIRECT);
        HttpSession session = request.getSession();
        UserRole sessionRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);
        CommentService commentService = CommentServiceImpl.getInstance();
        try {
            List<Comment> comments;
            comments = commentService.findAll();
            request.setAttribute(RequestParameter.COMMENTS, comments);
            selectionOfInformationDisplay(request, comments.size(), currentPage, sessionRole);
            request.setAttribute(RequestParameter.COMMAND, CommandType.TO_ABOUT_US_PAGE);
            router = new Router(PagePath.ABOUT_US_PAGE);
        } catch (ServiceException e) {
            logger.error("Error during go to about us page command", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }

    private void selectionOfInformationDisplay(HttpServletRequest request,
                                               int commentsSize,
                                               int currentPage,
                                               UserRole sessionRole) throws ServiceException {
        UserService userService = UserServiceImpl.getInstance();
        Optional<User> user = userService.findById(ADMIN_ID);

        if (user.isPresent() && user.get().getRole().equals(UserRole.ADMIN)
                && sessionRole.equals(UserRole.ADMIN)) {
            DisplayOfRatingAndPagination(request, user.get().getAverageRating(), commentsSize, currentPage,
                    PageSplitParameter.NUMBER_OF_COMMENTS_PER_PAGE_ADMIN);
        } else if (user.isPresent() && user.get().getRole().equals(UserRole.ADMIN)
                && !sessionRole.equals(UserRole.ADMIN)) {
            DisplayOfRatingAndPagination(request, user.get().getAverageRating(), commentsSize, currentPage,
                    PageSplitParameter.NUMBER_OF_COMMENTS_PER_PAGE_USER);
        } else {
            DisplayOfRatingAndPagination(request, 0D, commentsSize, currentPage,
                    PageSplitParameter.NUMBER_OF_COMMENTS_PER_PAGE_USER);
        }
    }

    private void DisplayOfRatingAndPagination(HttpServletRequest request,
                                              Double rating,
                                              int commentsSize,
                                              int currentPage,
                                              int numberOfCommentsPerPage) {
        SendSplitParameters sendSplitParameters = SendSplitParameters.getInstance();
        request.setAttribute(RequestParameter.RATING, rating);
        sendSplitParameters.sendSplitParametersComments(
                request,
                commentsSize,
                currentPage,
                numberOfCommentsPerPage);
    }
}
