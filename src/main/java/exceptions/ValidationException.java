package exceptions;

public class ValidationException extends AdoptionException {

    public ValidationException(String message) {
        super("Error de validación: " + message);
    }

    public ValidationException(String message, Throwable cause) {
        super("Error de validación: " + message, cause);
    }
}