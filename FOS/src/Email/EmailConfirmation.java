package Email;

/**
 * Create by Emmanuel and Yinsheng
 */


import org.hazlewood.connor.bottema.emailaddress.EmailAddressValidator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Logger;

public class EmailConfirmation {
    private String email;
    private String messageCode;
    public String message="";

    private boolean checkEmailFormat()
    {
        return EmailAddressValidator.isValid(email);
    }


    private void sendConfirmationEmail() {

        if(!checkEmailFormat())
        {
            message = "Not valid email format";
            return;
        }

        Properties props = new Properties();
        props.setProperty ("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "" + 587);
        props.setProperty("mail.smtp.starttls.enable", "true");
        Session mailSession = Session.getDefaultInstance(props,new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("coldfireworkk", "qwert1234");
            }
        });
        mailSession.setDebug(true);
        try {
            Transport transport = mailSession.getTransport();
            MimeMessage message = new MimeMessage(mailSession);
            message.setSubject("Confirmation Code");
            InternetAddress address = new InternetAddress("Team@FOS.com");
            message.setFrom(address);
            message.setContent("<h1>Welcome to FOS</h1> \n Your confirmation code: "+messageCode,"text/html");
            message.addRecipient (Message.RecipientType.TO,
                    new InternetAddress (email));
            transport.connect();
            transport.sendMessage(message, message.getRecipients (Message.RecipientType.TO));

        }catch (MessagingException e)
        {
            System.out.println(e.toString());
        }

        Logger.getAnonymousLogger().info("Email has been sent to " + email);
    }

    public EmailConfirmation(String messageCode, String email)
    {
        this.messageCode = messageCode;
        this.email = email;
        sendConfirmationEmail();
    }


    public static void main(String arg[])
    {
        EmailConfirmation email = new EmailConfirmation("hello","1145999511@qq.com");
        email.sendConfirmationEmail();
        System.out.println(email.message);
    }
}
