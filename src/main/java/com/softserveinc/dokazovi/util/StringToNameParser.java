package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.payload.SignUpRequest;
import com.softserveinc.dokazovi.security.oauth2.user.OAuth2UserInfo;

import java.util.Arrays;
import java.util.List;

public class StringToNameParser {

	public static void setUserNameFromRequest(SignUpRequest signUpRequest, UserEntity user) {
		List<String> strings = Arrays.asList(signUpRequest.getName().split(" "));
		parseString(strings, user);
	}

	public static void setUserNameFromRequest(OAuth2UserInfo oauth2UserInfo, UserEntity user) {
		List<String> strings = Arrays.asList(oauth2UserInfo.getName().split(" "));
		parseString(strings, user);
	}

	public static void parseString(List<String> strings, UserEntity user) {
		if (strings.isEmpty()) {
			user.setFirstName("user");
		}
		if (strings.size() == 2) {
			user.setFirstName(strings.get(0));
			user.setLastName(strings.get(1));
		}
		if (strings.size() != 2) {
			for (String n : strings) {
				if ((user.getFirstName() == null)) {
					user.setFirstName(n);
				} else {
					user.setFirstName(user.getFirstName() + " " + n);
				}
			}
		}

	}
}
