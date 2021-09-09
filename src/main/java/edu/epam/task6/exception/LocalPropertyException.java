package edu.epam.task6.exception;

public class LocalPropertyException extends Exception {
    public LocalPropertyException() {
    }

    public LocalPropertyException(String message) {
        super(message);
    }

    public LocalPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocalPropertyException(Throwable cause) {
        super(cause);
    }
}
