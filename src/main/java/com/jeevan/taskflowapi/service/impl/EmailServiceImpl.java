package com.jeevan.taskflowapi.service.impl;


import com.jeevan.taskflowapi.service.EmailService;

import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {



    private final JavaMailSender javaMailSender;

    @Value("${MAIL_USERNAME}")
    private String mailUsername;

    @Override
    public void sendEmail(
            String to,
            String subject,
            String message
    ){

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom(mailUsername);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        javaMailSender.send(mailMessage);
    }


}