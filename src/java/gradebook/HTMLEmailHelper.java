/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author allis
 */
public class HTMLEmailHelper {

    private final String email = "sistersOfSaintJoseph1@gmail.com";
    private final String username = "sistersofsaintjoseph1@gmail.com";
    private final String password = "areTheBest";
    private final String host = "smtp.gmail.com";
    private final int port = 465;
    
    private Properties props = null;
    private SMTPAuthenticator auth = null;
    private Session ses = null;
    
    public int send (String to, String subject, String body){
        int result = 0;
        
        props = new Properties();
        auth = new SMTPAuthenticator();
        ses = Session.getInstance(props, auth);
        
        MimeMessage msg = new MimeMessage(ses);
        try{
            msg.setContent(body, "text/html");
            msg.setSubject(subject);
            msg.setFrom(new InternetAddress(email));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            Transport transport = ses.getTransport("smtps");
            transport.connect(host, port, username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            
            result = 1;
        }catch (MessagingException e){
            e.printStackTrace();
            
        }
        return result;
    }
    
    public static class SMTPAuthenticator extends Authenticator {

        public PasswordAuthentication getPassworkAuthentication() {
            return new PasswordAuthentication("sistersofsaintjoseph1", "areTheBest");
        }
    }
}
