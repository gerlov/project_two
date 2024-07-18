package com.kth.project_dollarstore.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailService {


    @Autowired
    private JavaMailSender emailSender;

    public void sendPasswordResetEmail(String recipientEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("dollarstore@info.com");
        message.setTo(recipientEmail);
        message.setText(resetLink);
        emailSender.send(message);
        System.out.println("Mail Sent...");
    }
}
