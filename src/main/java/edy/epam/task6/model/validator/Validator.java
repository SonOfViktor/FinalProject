package edy.epam.task6.model.validator;

import edy.epam.task6.controller.command.RequestParameter;

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
    private final static String SIZE_REGEX = "[0-9]{1,9}";
    private final static String DISCOUNT_REGEX = "[0-9]{1,3}";

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

    public static boolean validatePassword(String password){
        boolean result = false;
        if(password != null && !password.isBlank()){
            result = password.matches(PASSWORD_REGEX);
        }
        return result;
    }

    public static boolean validatePrice(String price) {
        boolean result = false;
        if(price != null) {
            result = price.matches(PRICE_REGEX);
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

    public static boolean validateRegistrationPassword(String password, String repeatPassword) {
        boolean result = false;
        if (validatePassword(password) && validatePassword(repeatPassword)
                && password.equals(repeatPassword)) {
            result = true;
        }
        return result;
    }

    public static boolean validateOrder(Map<String, String> parameters) {
        String paid = parameters.get(RequestParameter.ORDER_PAID);
        String tattooId = parameters.get(RequestParameter.ORDER_TATTOO_ID);
        return validatePrice(paid) && validateId(tattooId);
    }

    public static boolean validateTattoo(Map<String, String> parameters) {
        String name = parameters.get(RequestParameter.TATTOO_NAME);
        String description = parameters.get(RequestParameter.TATTOO_DESCRIPTION);
        String price = parameters.get(RequestParameter.TATTOO_PRICE);
        String width = parameters.get(RequestParameter.TATTOO_WIDTH);
        String height = parameters.get(RequestParameter.TATTOO_HEIGHT);
        return validateName(name)
                && validateDescription(description)
                && validatePrice(price)
                && validateSize(width)
                && validateSize(height);
    }

    public static boolean validateUpdateTattooPrice(Map<String, String> parameters) {
        String price = parameters.get(RequestParameter.TATTOO_PRICE);
        return validatePrice(price);
    }

    public static boolean validateUser(Map<String, String> parameters) {
        String email = parameters.get(RequestParameter.USER_EMAIL);
        String login = parameters.get(RequestParameter.USER_LOGIN);
        String password = parameters.get(RequestParameter.USER_PASSWORD);
        String repeatPassword = parameters.get(RequestParameter.USER_REPEAT_PASSWORD);
        String name = parameters.get(RequestParameter.USER_NAME);
        String surname = parameters.get(RequestParameter.USER_SURNAME);
        return validateEmail(email)
                && validateLogin(login)
                && validateRegistrationPassword(password, repeatPassword)
                && validateName(name)
                && validateName(surname);
    }
}
