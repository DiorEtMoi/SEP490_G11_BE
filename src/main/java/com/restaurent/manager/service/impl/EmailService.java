package com.restaurent.manager.service.impl;

import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.service.IEmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class EmailService implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void sendEmail(String email, String body, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("VietKitchen");
        simpleMailMessage.setTo(email);
        simpleMailMessage.setText(body);
        simpleMailMessage.setSubject(subject);

        try {
            javaMailSender.send(simpleMailMessage);
        }catch (MailException e){
           throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String generateCode(int length) {
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String output = Integer.toString(randomNumber);
        while (output.length() < length){
            output = "0" + output;
        }
        return output;
    }

}
