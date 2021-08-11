package edy.epam.task6.model.validator;

public class EmailValidator {
    private static final String EMAIL_REGEX = "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,100})";

    public static boolean validate(String email) {
        boolean result = false;
        if(email != null && !email.isBlank()){
            result = email.matches(EMAIL_REGEX);
        }
        return result;
    }

}
