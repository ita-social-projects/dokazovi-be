package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MailSenderUtil {
    private final JavaMailSender javaMailSender;
    private final UserService userService;

    public void sendMessage(UserEntity user) throws IOException, MessagingException {
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);
        String confirmationUrl = "http://localhost:8080/api/auth/verification?token=" + token;
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String template = readHtmlFile("verificationMail.html");
        template = MessageFormat.format(template, confirmationUrl);
        message.setContent(template, "text/html");
        helper.setTo(user.getEmail());
        helper.setSubject("DOKAZOVI");
        javaMailSender.send(message);
    }

    public String readHtmlFile(String fileName) throws IOException {
        String file = "src/main/resources/template/" + fileName;
        return Files.readString(Path.of(file));
    }
}
