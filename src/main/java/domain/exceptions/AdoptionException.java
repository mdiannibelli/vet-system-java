package domain.exceptions;

public class AdoptionException extends Exception {
    public AdoptionException(String message) {
        super(message);
    }

    public static AdoptionException PetNotAvailable(String message) {
        return new AdoptionException(message);
    }

    public static AdoptionException InvalidCredentials(String message) {
        return new AdoptionException(message);
    }
}
