/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uy.com.cinestar.mdb;

import java.util.Properties;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import uy.com.cinestar.exceptions.EmailException;

/**
 *
 * @author Gonza
 */
@Stateless
@LocalBean
public class MailBean {

    public void sendMail(String mensaje) throws EmailException {
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp.gmail.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.starttls.required", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "cinestar.ventas.aut@gmail.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(props, new GmailAuthenticator("cinestar.ventas.aut@gmail.com",
                    "CinestarObl"));

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("cinestar.ventas.aut@gmail.com"));
            String subject;

            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gonzalodepro@gmail.com"));
            subject = "Cinestar - Venta entrada/s.";
            message.setContent(mensaje, "text/html");
            message.setSubject(subject);

            Transport tr = session.getTransport("smtp");
            tr.connect("cinestar.ventas.aut@gmail.com", "CinestarObl");
            tr.sendMessage(message, message.getAllRecipients());
            tr.close();
        } catch (MessagingException ex) {
            throw new EmailException("Error! Ocurrio un error en el envio de mail.", ex);
        }
    }
}
