package edy.epam.task6.model.validator;

public class StringValidator {

    public static boolean validate(String value) {
        return !(value == null || value.isBlank());
    }

}
