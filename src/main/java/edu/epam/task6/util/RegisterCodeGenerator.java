package edu.epam.task6.util;

public class RegisterCodeGenerator {

    private static final Integer SIZE = 1000000;

    public static String generateCode() {
        int code = (int) (Math.random() * SIZE);
        while (code < 100000 || code > 999999) {
            code = (int) (Math.random() * SIZE);
        }
        return String.valueOf(code);
    }
}
