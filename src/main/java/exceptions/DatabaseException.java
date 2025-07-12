package exceptions;

public class DatabaseException extends AdoptionException {

    public DatabaseException(String message) {
        super("Error de base de datos: " + message);
    }

    public DatabaseException(String message, Throwable cause) {
        super("Error de base de datos: " + message, cause);
    }
}