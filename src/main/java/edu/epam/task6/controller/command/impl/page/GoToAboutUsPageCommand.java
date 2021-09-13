package edu.epam.task6.controller.command.impl.page;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.controller.command.impl.pagination.SendSplitParameters;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.Comment;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.service.CommentService;
import edu.epam.task6.model.service.OrderService;
import edu.epam.task6.model.service.UserService;
import edu.epam.task6.model.service.impl.CommentServiceImpl;
import edu.epam.task6.model.service.impl.OrderServiceImpl;
import edu.epam.task6.model.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public class GoToAboutUsPageCommand implements Command {

    private static final Long ADMIN_ID = 1L;
    @Override
    public Router execute(HttpServletRequest request) {
        Router router;

        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.PREVIOUS_PAGE, PagePath.ABOUT_US_PAGE_REDIRECT);

        CommentService commentService = new CommentServiceImpl();
        UserService userService = new UserServiceImpl();
        try {
            List<Comment> comments;
            comments = commentService.findAll();
            request.setAttribute(RequestParameter.COMMENTS, comments);

            Optional<User> user = userService.findById(ADMIN_ID);
            if (user.isPresent() && user.get().getRole().equals(UserRole.ADMIN)) {
                Double averageRating = user.get().getAverageRating();
                request.setAttribute(RequestParameter.RATING, averageRating);
            }

            request = SendSplitParameters.sendSplitParametersOrders(request, comments.size());
            router = new Router(PagePath.ABOUT_US_PAGE);
        } catch (ServiceException e) {
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
