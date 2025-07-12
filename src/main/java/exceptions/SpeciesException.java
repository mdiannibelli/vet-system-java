package exceptions;

public class SpeciesException extends AdoptionException {

    public SpeciesException(String message) {
        super("Error de especie: " + message);
    }

    public SpeciesException(String message, Throwable cause) {
        super("Error de especie: " + message, cause);
    }
}