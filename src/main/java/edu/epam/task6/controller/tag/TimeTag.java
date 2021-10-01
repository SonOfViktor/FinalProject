package edu.epam.task6.controller.tag;

import edu.epam.task6.controller.command.SessionAttribute;
import edu.epam.task6.model.entity.User;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.TagSupport;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        User user = (User) session.getAttribute(SessionAttribute.USER);
        try {
            LocalDateTime userTime = user.getRegistrationDate();
            String date = userTime.toLocalDate().format(DateTimeFormatter.ofPattern ( "dd-MM-yyyy" ));
            LocalTime time = userTime.toLocalTime();
            StringBuilder stringBuilder = new StringBuilder(date + " " + time);
            pageContext.getOut().write(stringBuilder.toString());
            return SKIP_BODY;
        } catch(IOException e) {
            throw new JspException("PaginationTag error: " + e.getMessage(), e);
        }
    }
}
