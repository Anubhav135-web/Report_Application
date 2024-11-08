package com.jrtp.utility;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String subject, ByteArrayOutputStream outputStream, String attachmentMessage) throws MessagingException, IOException {
        if (outputStream.size() == 0) {
            throw new IOException("Generated Excel file is empty.");
        }

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("anubhav102023@gmail.com");
        helper.setTo("anubhav102022@gmail.com");
        helper.setSubject(subject);
        helper.setText("Please find the attached report.");

        // Attach Excel file as a ByteArrayResource
        ByteArrayResource attachment = new ByteArrayResource(outputStream.toByteArray());
        helper.addAttachment(attachmentMessage, attachment);

        javaMailSender.send(message);
        System.out.println("Email sent successfully with attachment.");
    }
}
