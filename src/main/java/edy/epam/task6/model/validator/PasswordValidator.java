package edy.epam.task6.model.validator;

public class PasswordValidator {
    private final static String PASSWORD_REGEX = "[a-zA-Z\\d_-]{8,45}";

    private PasswordValidator(){};

    public static boolean validate(String password){
        boolean result = false;
        if(password != null && !password.isBlank()){
            result = password.matches(PASSWORD_REGEX);
        }
        return result;
    }
}
