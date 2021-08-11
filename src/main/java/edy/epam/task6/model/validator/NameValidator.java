package edy.epam.task6.model.validator;

public class NameValidator {
    private static final String NAME_REGEX = "[A-Z](\\w[^0-9]){1,40}";

    private NameValidator(){};

    public static boolean validate(String name){
        boolean result = false;
        if(name != null && !name.isBlank()){
            result = name.matches(NAME_REGEX);
        }
        return result;
    }
}
