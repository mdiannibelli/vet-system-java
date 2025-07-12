package exceptions;

public class AdoptionException extends Error {

    public AdoptionException(String message) {
        super(message);
    }

    public AdoptionException(String message, Throwable cause) {
        super(message, cause);
    }
}