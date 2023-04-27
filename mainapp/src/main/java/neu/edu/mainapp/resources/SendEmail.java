package neu.edu.mainapp.resources;


import java.util.Date;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	String fromEmail;
    String fromPwd;
    String toEmail;
    String subject;
    String text;
    
    public SendEmail() {
        this.fromEmail = "infomaple0@gmail.com";
        this.fromPwd = "wlgjpjtrfsyeuani";
        
    }
    
    public void sendEmailFunction(String toEmail, String subject, String text) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.text = text;
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
// Get a Properties object
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.store.protocol", "pop3");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.starttls.required", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        final String username = this.fromEmail;
        final String password = this.fromPwd;
        try{
          Session session = Session.getDefaultInstance(props, 
                          new Authenticator(){
                             protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                             }});


        // -- Create a new message --
           Message msg = new MimeMessage(session);
        // -- Set the FROM and TO fields --
           msg.setFrom(new InternetAddress(username));
           msg.setRecipients(Message.RecipientType.TO, 
                             InternetAddress.parse(this.toEmail,false));
           msg.setSubject(this.subject);
           msg.setText(this.text);
           msg.setSentDate(new Date());
           Transport.send(msg);
           System.out.println("Message sent.");
         }catch (MessagingException e){ 
        	 System.out.println("outtt");
           System.out.println("Error d'envoi, cause: " + e);
         }
    }
}
