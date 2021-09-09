package edu.epam.task6.util;

import edu.epam.task6.exception.LocalPropertyException;
import edu.epam.task6.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailSender extends Thread {
    private static final Logger logger = LogManager.getLogger();

    public static final String MAIL_PROPERTY_PATH = "mail.properties";
    public static final String USER_KEY = "mail.user.user";
    public static final String PASSWORD_KEY = "mail.user.password";
    public static final String MAIL_CONTENT_TYPE = "text/html";

    private String emailTo;
    private String title;
    private String text;
    public EmailSender(String emailTo, String title, String text) {
        this.emailTo = emailTo;
        this.title = title;
        this.text = text;
    }

    public boolean sendEmail() throws ServiceException {
        boolean result;
        PropertyReader reader = new PropertyReader();
        Properties properties;
        try {
            properties = reader.read(MAIL_PROPERTY_PATH);
        } catch (LocalPropertyException e) {
            logger.error("Error during reading properties for mail sending: ", e);
            throw new ServiceException("Error during reading properties for mail sending: ", e);
        }
        String user = properties.getProperty(USER_KEY);
        String password = properties.getProperty(PASSWORD_KEY);

        Session session = createSession(properties, user, password);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject(title);
            String content = text;
            message.setContent(content, MAIL_CONTENT_TYPE);
            Transport.send(message);
            result = true;
        } catch (MessagingException e) {
            logger.error("Error during email sending: ", e);
            result = false;
        }
        return result;
    }

    private Session createSession(Properties properties, String user, String password) {
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        return session;
    }

    public void run() {
        try {
            sendEmail();
        } catch (ServiceException e) {
            logger.error("Error during email sending: ", e);
        }
    }
}
