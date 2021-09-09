package edu.epam.task6.util;

public class RegisterCodeGenerator {

    private static final Integer SIZE = 1000000;

    public static String generateCode() {
        int code = (int) (Math.random() * SIZE);
        String result = String.valueOf(code);
        return result;
    }
}
