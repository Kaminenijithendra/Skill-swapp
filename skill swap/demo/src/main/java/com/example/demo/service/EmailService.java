package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Skill Swap App - OTP Verification");
        message.setText(
                "Hello,\n\n" +
                        "Your OTP for Skill Swap App verification is: " + otp + "\n\n" +
                        "This OTP is valid for 5 minutes.\n\n" +
                        "Do not share this OTP with anyone.\n\n" +
                        "Thanks,\n" +
                        "Skill Swap Team"
        );

        mailSender.send(message);
    }
}