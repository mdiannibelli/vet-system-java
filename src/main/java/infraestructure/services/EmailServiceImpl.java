package infraestructure.services;

import config.Configuration;
import domain.services.EmailService;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;



public class EmailServiceImpl implements EmailService {
    private final Configuration configuration = Configuration.getInstance();
    private final String username = configuration.getGmail();
    private final String password = configuration.getGmailAppPass();
    private final String host = "smtp.gmail.com";

    @Override
    public void sendEmail(String origin, String destiny, String matter, String body) {
        System.out.println("Sending email to " + destiny);
        System.out.println("Matter: " + matter);
        System.out.println("Message: " + body);

        Properties prop = new Properties();
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(
                prop,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(origin));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destiny));
            message.setSubject(matter);
            message.setText(body);

            Transport.send(message);
            System.out.println("Message sent");
        }catch(Throwable e) {
            System.out.println("Error at sending email: " +e.getMessage());
            e.printStackTrace();
        }
    }
}
