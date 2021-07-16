package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.PasswordResetTokenEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.service.PasswordResetTokenService;
import com.softserveinc.dokazovi.service.UserService;
import com.softserveinc.dokazovi.service.impl.MailSenderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailSenderServiceImplTest {
    @Mock
    private JavaMailSender javaMailSender;
    @Mock
    private UserService userService;
    @Mock
    private PasswordResetTokenService passwordResetTokenService;
    @InjectMocks
    private MailSenderServiceImpl mailSender;

    @Test
    void sendEmailWithToken() throws IOException, MessagingException {
        when(passwordResetTokenService.createPasswordResetTokenForUser(any(UserEntity.class), anyString())).thenReturn(null);
        doNothing().when(javaMailSender).send(any(SimpleMailMessage.class));
        String contextPath = "http://localhost:8080";
        String email = "user@mail.com";
        String password = "user";
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(password)
                .build();
        String token = UUID.randomUUID().toString();
        PasswordResetTokenEntity tokenEntity = passwordResetTokenService.createPasswordResetTokenForUser(user, token);
        mailSender.sendEmailWithToken(contextPath, token, user);
        verify(passwordResetTokenService, times(1)).createPasswordResetTokenForUser(any(UserEntity.class), anyString());
        verify(javaMailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void readHtml() throws IOException {
        String fileName = "verificationMail.html";
        String result = mailSender.readHtmlFile(fileName);
        assertTrue(result.contains("table"));
    }
}
