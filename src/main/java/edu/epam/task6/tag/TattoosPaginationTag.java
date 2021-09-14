package edu.epam.task6.tag;

import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.Tattoo;
import edu.epam.task6.model.entity.TattooStatus;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TattoosPaginationTag extends TagSupport {

    private static final String CONTENT_PATH = "language";
    private static final String TITLE_TATTOOS_HOME = "home";
    public static final String TITLE_TATTOOS_ALL = "all";
    public static final String TITLE_TATTOOS_ACTIVE = "active";
    public static final String TITLE_TATTOOS_LOCKED = "locked";
    public static final String TITLE_TATTOOS_FOUNDED = "founded";
    public static final String TITLE_TATTOOS_PROPOSAL = "proposal";

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

            List<Tattoo> tattoos = (List<Tattoo>) request.getAttribute(RequestParameter.CATALOG);
            int start = (currentPage - 1) * elementsPerPage;
            int end =Math.min(tattoos.size(), currentPage * elementsPerPage);
            for (int i = start; i < end; i++) {
                Tattoo tattoo = tattoos.get(i);

                StringBuilder stringBuilder = new StringBuilder("<div class=\"main-grid-item\" title=\"");
                if (tattoo.getDescription() != null) {
                    stringBuilder.append(tattoo.getDescription());
                }
                stringBuilder.append("\">\n")
                        .append("<ul>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.number-tattoo"))
                        .append(" ")
                        .append(tattoo.getTattooId())
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.name-tattoo"))
                        .append(" ")
                        .append(tattoo.getName())
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.price-tattoo"))
                        .append(" ")
                        .append(tattoo.getPrice())
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.width-tattoo"))
                        .append(" ")
                        .append(tattoo.getWidth())
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.height-tattoo"))
                        .append(" ")
                        .append(tattoo.getHeight())
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.image-tattoo"))
                        .append(" ")
                        .append("<a href=\"")
                        .append(tattoo.getImageUrl())
                        .append("\">")
                        .append(resourceBundle.getString("tattoo.click-tattoo"))
                        .append("</a>")
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.body-part-tattoo"))
                        .append(" ")
                        .append(tattoo.getPlaces())
                        .append("</li>");

                stringBuilder.append("<li class=\"main-text\">")
                        .append(resourceBundle.getString("tattoo.rating-tattoo"))
                        .append(" ")
                        .append(tattoo.getAverageRating())
                        .append("</li>")
                        .append("</ul>");

                if(title.equals(TITLE_TATTOOS_ACTIVE) || title.equals(TITLE_TATTOOS_FOUNDED)) {
                    stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"create_order_command\"/>")
                            .append("<input type=\"hidden\" name=\"tattoo_id\" value=\"")
                            .append(tattoo.getTattooId())
                            .append("\"/>")
                            .append("<input id=\"date\" type=\"hidden\" name=\"registration_date\"/>")
                            .append("<button class=\"tattoo-item-button\" type=\"submit\" onclick=\"timeNow(date)\">")
                            .append(resourceBundle.getString("tattoo.order"))
                            .append("</button>")
                            .append("</form>");
                }

                if(title.equals(TITLE_TATTOOS_ALL) ||
                        title.equals(TITLE_TATTOOS_LOCKED) ||
                        title.equals(TITLE_TATTOOS_PROPOSAL)) {
                    if (tattoo.getStatus().equals(TattooStatus.ACTIVE)) {
                        stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                                .append("<input type=\"hidden\" name=\"command\" value=\"change_tattoo_status_command\"/>")
                                .append("<input type=\"hidden\" name=\"id\" value=\"")
                                .append(tattoo.getTattooId())
                                .append("\"/>")
                                .append("<input type=\"hidden\" name=\"active\" value=\"false\"/>")
                                .append("<button class=\"tattoo-item-button\" type=\"submit\">")
                                .append(resourceBundle.getString("tattoo.block"))
                                .append("</button>")
                                .append("</form>");
                    }
                    if (tattoo.getStatus().equals(TattooStatus.LOCKED)) {
                        stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                                .append("<input type=\"hidden\" name=\"command\" value=\"change_tattoo_status_command\"/>")
                                .append("<input type=\"hidden\" name=\"id\" value=\"")
                                .append(tattoo.getTattooId())
                                .append("\"/>")
                                .append("<input type=\"hidden\" name=\"active\" value=\"true\"/>")
                                .append("<button class=\"tattoo-item-button\" type=\"submit\">")
                                .append(resourceBundle.getString("tattoo.unblock"))
                                .append("</button>")
                                .append("</form>");
                    }
                    if (tattoo.getStatus().equals(TattooStatus.OFFERED_BY_USER)) {
                        stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                                .append("<input type=\"hidden\" name=\"command\" value=\"to_approve_tattoo_page_command\"/>")
                                .append("<input type=\"hidden\" name=\"id\" value=\"")
                                .append(tattoo.getTattooId())
                                .append("\"/>")
                                .append("<input type=\"hidden\" name=\"active\" value=\"true\"/>")
                                .append("<button class=\"tattoo-item-button\" type=\"submit\">")
                                .append(resourceBundle.getString("tattoo.proposal"))
                                .append("</button>")
                                .append("</form>");
                    }
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