package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;

public interface MailSenderService {

	void sendEmailWithToken (String contextPath, String token, UserEntity user);

}
