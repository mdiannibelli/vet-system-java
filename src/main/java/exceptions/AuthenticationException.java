package exceptions;

public class AuthenticationException extends AdoptionException {

    public AuthenticationException(String message) {
        super("Error de autenticación: " + message);
    }

    public AuthenticationException(String message, Throwable cause) {
        super("Error de autenticación: " + message, cause);
    }
}