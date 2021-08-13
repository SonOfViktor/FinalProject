package edy.epam.task6.model.validator;

import java.math.BigDecimal;

public class MainValidator {

    private static final String EMAIL_REGEX = "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,100})";
    private final static String LOGIN_REGEX= "[a-zA-Z][\\w._-]{1,40}";
    private static final String NAME_REGEX = "[A-Z](\\w[^0-9]){1,40}";
    private final static String PASSWORD_REGEX = "[a-zA-Z\\d_-]{8,45}";

    public static boolean validateEmail(String email) {
        boolean result = false;
        if(email != null && !email.isBlank()){
            result = email.matches(EMAIL_REGEX);
        }
        return result;
    }

    public static boolean validateLogin(String login){
        boolean result = false;
        if(login != null && !login.isBlank()){
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

    public static boolean validateName(String name){
        boolean result = false;
        if(name != null && !name.isBlank()){
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

    public static boolean validateRegistrationPassword(String password, String repeatPassword) {
        boolean result = false;
        if (PasswordValidator.validate(password) && PasswordValidator.validate(repeatPassword)
                && password.equals(repeatPassword)) {
            result = true;
        }
        return result;
    }

    public static boolean validateString(String value) {
        return !(value == null || value.isBlank());
    }
}
