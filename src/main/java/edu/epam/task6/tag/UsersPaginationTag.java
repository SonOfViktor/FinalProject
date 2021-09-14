package edu.epam.task6.tag;

import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.User;
import edu.epam.task6.model.entity.UserRole;
import edu.epam.task6.model.entity.UserStatus;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class UsersPaginationTag extends TagSupport {

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

            List<User> users = (List<User>) request.getAttribute(RequestParameter.USERS);
            int start = (currentPage - 1) * elementsPerPage;
            int end = Math.min(users.size(), currentPage * elementsPerPage);
            for (int i = start; i < end; i++) {
                User user = users.get(i);

                StringBuilder stringBuilder = new StringBuilder("<div class=\"user-list-item\">");
                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.id"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getUserId())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.email"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getEmail())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.login"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getLogin())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.name"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getName())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.surname"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getSurname())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.discount"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getDiscount())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.balance"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getBalance())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.registration-date"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getRegistrationDate())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.role"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getRole())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"user-list-text-item\">")
                        .append("<div class=\"user-list-text1\">")
                        .append(resourceBundle.getString("users.user.status"))
                        .append("</div>")
                        .append("<div class=\"user-list-text2\">")
                        .append(user.getStatus())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                        .append("<input type=\"hidden\" name=\"command\" value=\"change_user_status_command\"/>")
                        .append("<input type=\"hidden\" name=\"id\" value=\"")
                        .append(user.getUserId())
                        .append("\"/>")
                        .append("<input type=\"hidden\" name=\"active\" value=\"false\"/>")
                        .append("<input type=\"hidden\" name=\"title_users\" value=\"")
                        .append(title)
                        .append("\"/>");

                if (user.getStatus().equals(UserStatus.ACTIVE) && !user.getRole().equals(UserRole.ADMIN)) {
                    stringBuilder.append("<button class=\"user-list-item-button\" type=\"submit\">")
                            .append(resourceBundle.getString("change.button-block"))
                            .append("</button>");
                }
                if (user.getStatus().equals(UserStatus.BLOCKED) && user.getRole().equals(UserRole.ADMIN)) {
                    stringBuilder.append("<button disabled=\"disabled\" class=\"user-list-item-button\" type=\"submit\">")
                            .append(resourceBundle.getString("change.button-block"))
                            .append("</button>");
                }

                stringBuilder.append("</form>")
                        .append("<form class=\"form-button-user2\" method=\"post\" action=\"ProjectServlet\">")
                        .append("<input type=\"hidden\" name=\"command\" value=\"change_user_status_command\"/>")
                        .append("<input type=\"hidden\" name=\"id\" value=\"")
                        .append(user.getUserId())
                        .append("\"/>")
                        .append("<input type=\"hidden\" name=\"active\" value=\"true\"/>")
                        .append("<input type=\"hidden\" name=\"title_users\" value=\"")
                        .append(title)
                        .append("\"/>");

                if (user.getStatus().equals(UserStatus.ACTIVE) && user.getRole().equals(UserRole.ADMIN)) {
                    stringBuilder.append("<button disabled=\"disabled\" class=\"user-list-item-button\" type=\"submit\">")
                            .append(resourceBundle.getString("change.button-unblock"))
                            .append("</button>");
                }
                if (user.getStatus().equals(UserStatus.BLOCKED) && !user.getRole().equals(UserRole.ADMIN)) {
                    stringBuilder.append("<button class=\"user-list-item-button\" type=\"submit\">")
                            .append(resourceBundle.getString("change.button-unblock"))
                            .append("</button>");
                }

                stringBuilder.append("</form>")
                        .append("</div>");

                pageContext.getOut().write(stringBuilder.toString());
            }
            return SKIP_BODY;
        } catch(IOException e) {
            throw new JspException("PaginationTag error: " + e.getMessage(), e);
        }
    }
}
