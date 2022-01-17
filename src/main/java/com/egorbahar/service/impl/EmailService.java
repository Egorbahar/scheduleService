package com.egorbahar.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
@EnableScheduling
public class EmailService {
    private JavaMailSender mailSender;

    public void sendEmail() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("leshkov.1998@mail.ru");
        message.setSubject("Test Simple Email");
        message.setText("This is fucking test, UNUCHEK IDI NAHUI");
        mailSender.send(message);

    }
}
