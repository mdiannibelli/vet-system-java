package infraestructure.helpers;


import domain.services.EmailService;
import infraestructure.services.EmailServiceImpl;

public class EmailHelper {
    private static final EmailService emailService = new EmailServiceImpl();

    public static void sendEmail(String origin, String destiny, String matter, String body) {
        emailService.sendEmail(origin, destiny, matter, body);
    }
}
