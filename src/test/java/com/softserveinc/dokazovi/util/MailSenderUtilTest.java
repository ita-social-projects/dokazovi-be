package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.service.UserService;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MailSenderUtilTest {
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private UserService userService;
    @InjectMocks
    private MailSenderUtil mailSender;

    @Test
    void sendMessage() throws IOException, MessagingException {
        String email = "user@mail.com";
        String password = "user";
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(password)
                .build();
        MimeMessage message = new MimeMessage(Session.getInstance(new Properties()));
        doNothing().when(userService).createVerificationToken(any(UserEntity.class), anyString());
        when(javaMailSender.createMimeMessage()).thenReturn(message);
        doNothing().when(javaMailSender).send(any(MimeMessage.class));
        mailSender.sendMessage(user);
        verify(userService, times(1)).createVerificationToken(any(UserEntity.class), anyString());
        verify(javaMailSender, times(1)).createMimeMessage();
        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    void readHtml() throws IOException {
        String fileName = "verificationMail.html";
        String result = mailSender.readHtmlFile(fileName);
        assertTrue(result.contains("table"));
    }
}
