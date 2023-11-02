package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.entity.UserEntity;

public interface MailSenderService {

    void sendEmailWithToken (String contextPath, String token, UserEntity user);

    void sendEmailWithActivationToken(String contextPath, String token, UserEntity user);

}
