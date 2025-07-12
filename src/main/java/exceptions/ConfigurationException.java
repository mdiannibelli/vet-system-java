package exceptions;

public class ConfigurationException extends AdoptionException {

    public ConfigurationException(String message) {
        super("Error de configuración: " + message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super("Error de configuración: " + message, cause);
    }
}