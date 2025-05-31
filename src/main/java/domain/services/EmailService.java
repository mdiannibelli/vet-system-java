package domain.services;

public interface EmailService {
    void sendEmail(String origin, String destiny, String matter, String body);
}
