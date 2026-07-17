package com.jeevan.taskflowapi.service;


public interface EmailService {


    void sendEmail(
            String to,
            String subject,
            String message
    );


}