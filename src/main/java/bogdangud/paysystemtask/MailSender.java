package bogdangud.paysystemtask;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSender {
    private String username;
    private String password;
    private Properties props;

    public MailSender(String username, String password) {
        this.username = username;
        this.password = password;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
    }


    public void send(String subject, String text, String fromEmail, String toEmail) {
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message mailMessage = new MimeMessage(session);
            mailMessage.setFrom(new InternetAddress(username));
            mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            mailMessage.setSubject(subject);
            mailMessage.setText(text);
            Transport.send(mailMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

