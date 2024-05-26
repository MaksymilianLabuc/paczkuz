package org.janbat.paczkuz;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class Mailer {
    private static final String username = "paczkuz@op.pl";
    private static final String password = "hacbeW-jupkor-xuzgi7";

    public static void sendMail(String emails_list, String title, String body) {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.poczta.onet.pl");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.user", username);
        prop.put("mail.smtp.password", password);
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(prop,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("paczkuz@op.pl"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emails_list)
            );
            message.setSubject(title);
            message.setText(body);

            Transport.send(message);

            System.out.println("E-mail został wysłany do " + emails_list);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
