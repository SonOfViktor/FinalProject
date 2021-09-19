package edu.epam.task6.tag;

import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.Order;
import edu.epam.task6.model.entity.OrderStatus;
import edu.epam.task6.model.entity.User;
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

public class OrdersPaginationTeg extends TagSupport {

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

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int doStartTag() throws JspException {
        try {
            ServletRequest request = pageContext.getRequest();
            HttpSession session = pageContext.getSession();
            UserRole userRole = (UserRole) session.getAttribute(SessionAttribute.ROLE);
            String attrLocale = (String) session.getAttribute(SessionAttribute.LOCALE);
            Locale locale = Locale.forLanguageTag(attrLocale.replace("_", "-"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle(CONTENT_PATH, locale);

            List<Order> orders = (List<Order>) request.getAttribute(RequestParameter.ORDERS);
            int start = (currentPage - 1) * elementsPerPage;
            int end = Math.min(orders.size(), currentPage * elementsPerPage);
            for (int i = start; i < end; i++) {
                Order order = orders.get(i);
                LocalDateTime orderTime = order.getRegistrationDate();
                String date = orderTime.toLocalDate().format(DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ));
                LocalTime time = orderTime.toLocalTime();

                StringBuilder stringBuilder = new StringBuilder("<div class=\"order-list-item\">");
                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.id"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getOrderId())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.paid"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getPaid())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.registration-date"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(date)
                        .append(" ")
                        .append(time)
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.login"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getUserLogin())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.status"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getOrderStatus())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.tattoo.id"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getTattooId())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.tattoo.price"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getTattooPrice())
                        .append("</div>")
                        .append("</div>");

                stringBuilder.append("<div class=\"order-list-text-item\">")
                        .append("<div class=\"order-list-text1\">")
                        .append(resourceBundle.getString("order.tattoo.name"))
                        .append("</div>")
                        .append("<div class=\"order-list-text2\">")
                        .append(order.getTattooName())
                        .append("</div>")
                        .append("</div>");

                if (order.getOrderStatus().equals(OrderStatus.ACTIVE) && userRole.equals(UserRole.USER)) {
                    stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"cancel_order_command\"/>")
                            .append("<input type=\"hidden\" name=\"id\" value=\"")
                            .append(order.getOrderId())
                            .append("\"/>")
                            .append("<button class=\"order-list-item-button\">")
                            .append(resourceBundle.getString("orders.cancel.button"))
                            .append("</button>")
                            .append("</form>");
                }

                if (order.getOrderStatus().equals(OrderStatus.ACTIVE) && userRole.equals(UserRole.ADMIN)) {
                    stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"cancel_order_command\"/>")
                            .append("<input type=\"hidden\" name=\"id\" value=\"")
                            .append(order.getOrderId())
                            .append("\"/>")
                            .append("<button class=\"order-list-item-button2\">")
                            .append(resourceBundle.getString("orders.cancel.button"))
                            .append("</button>")
                            .append("</form>");

                    stringBuilder.append("<form class=\"form-button-order2\" method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"complete_order_command\"/>")
                            .append("<input type=\"hidden\" name=\"id\" value=\"")
                            .append(order.getOrderId())
                            .append("\"/>")
                            .append("<button class=\"order-list-item-button2\">")
                            .append(resourceBundle.getString("orders.comlete.button"))
                            .append("</button>")
                            .append("</form>");
                }

                if (order.getOrderStatus().equals(OrderStatus.COMPLETED) && userRole.equals(UserRole.USER)) {
                    stringBuilder.append("<form method=\"post\" action=\"ProjectServlet\">")
                            .append("<input type=\"hidden\" name=\"command\" value=\"to_change_rating_page_command\"/>")
                            .append("<input type=\"hidden\" name=\"id\" value=\"")
                            .append(order.getOrderId())
                            .append("\"/>")
                            .append("<button class=\"order-list-item-button\">")
                            .append(resourceBundle.getString("change.rating.button"))
                            .append("</button>")
                            .append("</form>");
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