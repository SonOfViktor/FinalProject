package edu.epam.task6.model.validator;

import edu.epam.task6.controller.command.RequestParameter;
import edu.epam.task6.model.dao.ColumnName;

import java.math.BigDecimal;
import java.util.Map;

public class Validator {

    private static final String ID_REGEX = "[0-9]{1,19}";
    private static final String EMAIL_REGEX = "^[A-z0-9._-]+@[a-z0-9._-]+\\.[a-z]{2,4}$";
    private final static String LOGIN_REGEX= "[\\w][\\w._-]{0,39}";
    private static final String NAME_REGEX = "[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё]{0,39}";
    private final static String PASSWORD_REGEX = "[-\\w_!@#$%^&*()]{8,45}";
    private final static String PRICE_REGEX = "[0-9]([0-9.]){0,9}";
    private final static String DESCRIPTION_REGEX = "[A-ZА-ЯЁ][A-Za-zА-Яа-яЁё ,\\.!?\\d]{0,1999}";
    private final static String COMMENT_REGEX = "[A-Za-zА-Яа-яЁё1-9 ,\\.!?\\d]{1,1000}";
    private final static String SIZE_REGEX = "[0-9]{1,9}";
    private final static String DISCOUNT_REGEX = "[0-9]{1,3}";
    private final static String RATING_REGEX = "[0-9]{1,2}";

    private final static String MAX_PRICE = "9223372036854775807";

    public static boolean validateId(String id) {
        boolean result = false;
        if(id != null && !id.isBlank()) {
            result = id.matches(ID_REGEX);
        }
        return result;
    }

    public static boolean validateEmail(String email) {
        boolean result = false;
        if(email != null && !email.isBlank() && email.length() <= 50) {
            result = email.matches(EMAIL_REGEX);
        }
        return result;
    }

    public static boolean validateLogin(String login) {
        boolean result = false;
        if(login != null && !login.isBlank()) {
            result = login.matches(LOGIN_REGEX);
        }
        return result;
    }

    public static boolean validateMinMaxRange(BigDecimal min, BigDecimal max) {
        boolean result = false;
        if (min.doubleValue() < max.doubleValue() && min.doubleValue() > 0) {
            result = true;
        }
        return result;
    }

    public static boolean validateName(String name) {
        boolean result = false;
        if(name != null && !name.isBlank()) {
            result = name.matches(NAME_REGEX);
        }
        return result;
    }

    public static boolean validatePassword(String password) {
        boolean result = false;
        if(password != null && !password.isBlank()){
            result = password.matches(PASSWORD_REGEX);
        }
        return result;
    }

    public static boolean validatePrice(String price) {
        boolean result = false;
        if(price != null && price.matches(PRICE_REGEX)) {
            BigDecimal localPrice = new BigDecimal(price);
            BigDecimal maxPrice = new BigDecimal(MAX_PRICE);
            if (localPrice.compareTo(BigDecimal.ZERO) == 1 && localPrice.compareTo(maxPrice) == -1) {
                result = true;
            }
        }
        return result;
    }

    public static boolean validateDescription(String description) {
        boolean result = false;
        if(description != null && !description.isBlank()) {
            result = description.matches(DESCRIPTION_REGEX);
        }
        return result;
    }

    public static boolean validateComment(String comment) {
        boolean result = false;
        if(comment != null && !comment.isBlank()) {
            result = comment.matches(COMMENT_REGEX);
        }
        return result;
    }

    public static boolean validateSize(String size) {
        boolean result = false;
        if(size != null) {
            result = size.matches(SIZE_REGEX);
        }
        return result;
    }

    public static boolean validateDiscount(String discount) {
        boolean result = false;
        if(discount != null && discount.matches(DISCOUNT_REGEX)) {
            int localDiscount = Integer.valueOf(discount);
            if (localDiscount >= 0 && localDiscount <= 100) {
                result = true;
            }
        }
        return result;
    }

    public static boolean validateAverageRating(String grade) {
        boolean result = false;
        if(grade != null && grade.matches(DISCOUNT_REGEX)) {
            int localGrade = Integer.valueOf(grade);
            if (localGrade >= 1 && localGrade <= 10) {
                result = true;
            }
        }
        return result;
    }

    public static boolean validateRegistrationPassword(String password, String repeatPassword) {
        boolean result = false;
        if (validatePassword(password) && validatePassword(repeatPassword)
                && password.equals(repeatPassword)) {
            result = true;
        }
        return result;
    }

    public static boolean validateOrder(Map<String, String> parameters) {
        String paid = parameters.get(ColumnName.ORDERS_PAID);
        String tattooId = parameters.get(ColumnName.ORDERS_TATTOO_ID);
        return validatePrice(paid) && validateId(tattooId);
    }

    public static boolean validateTattoo(Map<String, String> parameters) {
        String name = parameters.get(ColumnName.TATTOOS_NAME);
        String description = parameters.get(ColumnName.TATTOOS_DESCRIPTION);
        String price = parameters.get(ColumnName.TATTOOS_PRICE);
        String width = parameters.get(ColumnName.TATTOOS_WIDTH);
        String height = parameters.get(ColumnName.TATTOOS_HEIGHT);
        return validateName(name)
                && validateDescription(description)
                && validatePrice(price)
                && validateSize(width)
                && validateSize(height);
    }

    public static boolean validateUser(Map<String, String> parameters) {
        String email = parameters.get(ColumnName.USER_EMAIL);
        String login = parameters.get(ColumnName.USER_LOGIN);
        String password = parameters.get(ColumnName.USER_PASSWORD);
        String repeatPassword = parameters.get(ColumnName.USER_REPEAT_PASSWORD);
        String name = parameters.get(ColumnName.USER_NAME);
        String surname = parameters.get(ColumnName.USER_SURNAME);
        return validateEmail(email)
                && validateLogin(login)
                && validateRegistrationPassword(password, repeatPassword)
                && validateName(name)
                && validateName(surname);
    }
}
