package com.configuracion;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

    public static void enviarEmail(String destinatario, String asunto, String cuerpo) {
       
        // Sender's email ID needs to be mentioned
        String remitente = "apuntecautn@gmail.com";

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("apuntecautn@gmail.com", "rvmkejpsnfqqcfon");
            }
        });
        // Used to debug SMTP issues
        session.setDebug(true);
        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(remitente));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            // Set Subject: header field
            message.setSubject(asunto);
            // Now set the actual message
            message.setText(cuerpo);
            System.out.println("Enviando...");            // Send message
            Transport.send(message);
            System.out.println("Mensaje enviado con éxito....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}