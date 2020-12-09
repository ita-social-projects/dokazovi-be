package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MailSenderUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserService userService;

    public void sendMessage(UserEntity user) {
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String confirmationUrl = "http://localhost:8080/api/auth/verification?token=" + token;
        String message = "Ви успішно зареєструвались. Щоб підтвердити Вашу почту, пройдіть за посиланням нижче:";

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("DOKAZOVI: Підтвердження електронної адреси");
        email.setText(message + "\r\n" + confirmationUrl);
        javaMailSender.send(email);
    }
}
