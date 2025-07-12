package exceptions;

public class EmployeeException extends AdoptionException {

    public EmployeeException(String message) {
        super("Error de empleado: " + message);
    }

    public EmployeeException(String message, Throwable cause) {
        super("Error de empleado: " + message, cause);
    }
}