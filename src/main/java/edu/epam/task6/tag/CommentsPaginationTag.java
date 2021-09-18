package edu.epam.task6.tag;

import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.Comment;
import edu.epam.task6.model.entity.UserRole;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class CommentsPaginationTag extends TagSupport {

    private static final String CONTENT_PATH = "language";

    private int currentPage;
    private int elementsPerPage;
    private String title;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setElementsPerPage(int elementsPerPage) {
        this.elementsPerPage = elementsPerPage;
    }

    public void setTitle(String title) { this.title = title; }

    @Override
    public int doStartTag() throws JspException {
        try {
            ServletRequest request = pageContext.getRequest();
            HttpSession session = pageContext.getSession();
            String attrLocale = (String) session.getAttribute(SessionAttribute.LOCALE);
            Locale locale = Locale.forLanguageTag(attrLocale.replace("_", "-"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle(CONTENT_PATH, locale);

            List<Comment> comments = (List<Comment>) request.getAttribute(RequestParameter.COMMENTS);
            int start = (currentPage - 1) * elementsPerPage;
            int end = Math.min(comments.size(), currentPage * elementsPerPage);
            for (int i = start; i < end; i++) {
                Comment comment = comments.get(i);
                LocalDateTime commentTime = comment.getRegistrationDate();
                String date = commentTime.toLocalDate().format(DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ));
                LocalTime time = commentTime.toLocalTime();

                StringBuilder stringBuilder = new StringBuilder("<div class=\"comments-list-item\">");
                stringBuilder.append("<div class=\"comments-list-text-item1\">")
                        .append("<div class=\"comments-list-text1\">")
                        .append(comment.getUserLogin())
                        .append(": ")
                        .append(date)
                        .append(" ")
                        .append(time)
                        .append("</div>")
                        .append("</div>")
                        .append("<div class=\"comments-list-text-item2\">")
                        .append("<div class=\"comments-list-text2\">")
                        .append(comment.getText())
                        .append("</div>")
                        .append("</div>");

                if (session.getAttribute(SessionAttribute.ROLE).equals(UserRole.ADMIN)) {
                    stringBuilder.append("<div class=\"container-button-comments\">")
                            .append("<form class=\"form-button-comment1\" method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"delete_comment_command\"/>")
                            .append("<input type=\"hidden\" name=\"comment_id\" value=\"")
                            .append(comment.getCommentId())
                            .append("\"/>")
                            .append("<button class=\"comments-list-item-button\" type=\"submit\">")
                            .append(resourceBundle.getString("about.us.delete.comment"))
                            .append("</button>")
                            .append("</form>");

                    stringBuilder.append("<form class=\"form-button-comment2\" method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"change_user_status_command\"/>")
                            .append("<input type=\"hidden\" name=\"id\" value=\"")
                            .append(comment.getUserId())
                            .append("\"/>")
                            .append("<input type=\"hidden\" name=\"active\" value=\"false\"/>")
                            .append("<button class=\"comments-list-item-button\" type=\"submit\">")
                            .append(resourceBundle.getString("about.us.block.user"))
                            .append("</button>")
                            .append("</form>")
                            .append("</div>");
                }

                stringBuilder.append("</div>");


                pageContext.getOut().write(stringBuilder.toString());
            }
            return SKIP_BODY;
        } catch (IOException e) {
            throw new JspException("PaginationTag error: " + e.getMessage(), e);
        }
    }
}