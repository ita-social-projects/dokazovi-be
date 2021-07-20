package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.service.MailSenderService;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.UUID;

import static com.softserveinc.dokazovi.controller.EndPoints.USER;
import static com.softserveinc.dokazovi.controller.EndPoints.USER_CHECK_TOKEN;

@Service
@RequiredArgsConstructor
public class MailSenderServiceImpl implements MailSenderService {

    private final JavaMailSender mailSender;
    private final UserService userService;
    @Value("${host.url}")
    private String hostUrl;

    public void sendMessage(UserEntity user) throws IOException, MessagingException {
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        String confirmationUrl = hostUrl + "/api/auth/verification?token=" + token;
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String template = readHtmlFile("verificationMail.html");
        template = MessageFormat.format(template, confirmationUrl);
        message.setContent(template, "text/html");
        helper.setTo(user.getEmail());
        helper.setSubject("DOKAZOVI");
        mailSender.send(message);
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
        String url = contextPath + USER + USER_CHECK_TOKEN + "?token=" + token;
        String message = "Change your password after clicking reference below."
                + "If you didn't request to change password, please won't do anything";
        return constructEmail("Reset password", message + "\r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, UserEntity user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        return email;
    }
}
