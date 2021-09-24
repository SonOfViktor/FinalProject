package edu.epam.task6.controller.command.impl;

import edu.epam.task6.controller.command.*;
import edu.epam.task6.exception.ServiceException;
import edu.epam.task6.model.dao.ColumnName;
import edu.epam.task6.model.service.CommentService;
import edu.epam.task6.model.service.impl.CommentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CreateCommentCommand implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute(SessionAttribute.USER_ID);
        CommentService commentService = CommentServiceImpl.getInstance();
        Map<String, String> parameters = new HashMap<>();
        try {
            parameters.put(ColumnName.COMMENT_TEXT, request.getParameter(RequestParameter.COMMENT_TEXT));
            parameters.put(ColumnName.COMMENT_REGISTRATION_DATE,
                    request.getParameter(RequestParameter.COMMENT_REGISTRATION_DATE));
            parameters.put(ColumnName.COMMENT_USER_ID, userId.toString());

            if (commentService.leaveComment(parameters)) {
                logger.info("Comment created successfully");
                router = new Router(Router.RouterType.REDIRECT,
                        session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            } else {
                logger.error("Error during creating comment, perhaps the comment was not validated.");
                router = new Router(Router.RouterType.REDIRECT,
                        session.getAttribute(SessionAttribute.PREVIOUS_PAGE).toString());
            }
        } catch (ServiceException e) {
            logger.error("Error during creating comment: ", e);
            router = new Router(PagePath.ERROR_PAGE_500);
        }
        return router;
    }
}
