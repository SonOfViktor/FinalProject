package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.service.CommentService;
import edu.epam.task6.model.service.impl.CommentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeleteCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);
        CommentService commentService = CommentServiceImpl.getInstance();
        try {
            if (userRole.equals(UserRole.ADMIN)) {
                Long commentId = Long.valueOf(request.getParameter(RequestParameter.COMMENT_ID));

                if (commentService.deleteComment(commentId)) {
                    logger.info("Comment deleted successfully");
                } else {
                    logger.error("Error during deleting comment.");
                }
                router = new Router(Router.RouterType.REDIRECT,
                        session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            } else {
                logger.error("An attempt to delete a comment without an administrator role.");
                router = new Router(PagePath.ERROR_PAGE_500);
            }
        } catch (ServiceException e) {
            logger.error("Error during deleting comment: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
