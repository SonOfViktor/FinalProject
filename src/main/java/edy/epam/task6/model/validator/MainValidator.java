package edy.epam.task6.model.validator;

public class MainValidator {

    public static boolean registrationPasswordValidate(String password, String repeatPassword) {
        boolean result = false;
        if (PasswordValidator.validate(password) && PasswordValidator.validate(repeatPassword)
                && password.equals(repeatPassword)) {
            result = true;
        }
        return result;
    }
}
