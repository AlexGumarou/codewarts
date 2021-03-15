package com.codewarts.service;

import com.codewarts.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {

    private JavaMailSender mailSender;

    @Autowired
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Value("${spring.mail.username}")
    private String username;

    public void send(String emailTo, String subject, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }

    public String messageCreateStaff(Staff staff) {
        return String.format(
                "Была зарегистрирована новая штатная единица: \nЛогин: %s\nРоль: %s\nФилиал: %s\n",
                staff.getLogin(),
                staff.getStaffRole().getRole(),
                staff.getDepartment().getName()
        );
    }
}
