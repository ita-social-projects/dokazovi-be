package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHANGE_PASSWORD;

@Service
@RequiredArgsConstructor
public class MailSenderUtil implements MailSenderService {

    private final JavaMailSender mailSender;

    public void sendMessage(UserEntity user) throws IOException, MessagingException {

    }

    @Override
    public void sendEmailWithToken (String contextPath, String token, UserEntity user) {
        mailSender.send(constructResetTokenEmail(contextPath, token, user));
    }

    public String readHtmlFile(String fileName) throws IOException {
        String file = "src/main/resources/template/" + fileName;
        return Files.readString(Path.of(file));
    }

    private SimpleMailMessage constructResetTokenEmail(
            String contextPath, String token, UserEntity user) {
        String url = contextPath + USER + USER_CHANGE_PASSWORD + "?token=" + token;
        String message = "Change your password after clicking reference below."
                + "If you didn't request to change password, please won't do anything";
        return constructEmail("Reset password", message + "\r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, UserEntity user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("igor.zaharko.1986@gmail.com");
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        return email;
    }
}
