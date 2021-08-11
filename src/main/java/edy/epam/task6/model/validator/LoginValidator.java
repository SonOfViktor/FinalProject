package edy.epam.task6.model.validator;

public class LoginValidator {
    private final static String LOGIN_REGEX= "[a-zA-Z][\\w._-]{1,40}";

    private LoginValidator(){};

    public static boolean validate(String login){
        boolean result = false;
        if(login != null && !login.isBlank()){
            result = login.matches(LOGIN_REGEX);
        }
        return result;
    }
}
