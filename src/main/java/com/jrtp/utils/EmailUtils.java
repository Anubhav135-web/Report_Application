package com.jrtp.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendHtmlEmail(String to, String subject, String body) {
        boolean isSent = false;
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);

            System.out.println("Sending email to: " + to);  // Logging the send attempt
            mailSender.send(message);
            isSent = true;
            System.out.println("Email sent successfully."); // Logging success
        } catch (MessagingException e) {
            System.err.println("Failed to send email due to MessagingException: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Failed to send email due to unexpected exception: " + e.getMessage());
            e.printStackTrace();
        }
        return isSent;
    }
}
