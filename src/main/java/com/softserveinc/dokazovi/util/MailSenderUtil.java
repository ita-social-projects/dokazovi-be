package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class MailSenderUtil {

    private final JavaMailSender javaMailSender;
    private final UserService userService;

    public void sendMessage(UserEntity user) throws IOException, MessagingException {

    }

    public String readHtmlFile(String fileName) throws IOException {
        String file = "src/main/resources/template/" + fileName;
        return Files.readString(Path.of(file));
    }
}
