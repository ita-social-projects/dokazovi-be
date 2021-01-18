package com.softserveinc.dokazovi.util;

import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.payload.SignUpRequest;
import com.softserveinc.dokazovi.security.oauth2.user.OAuth2UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


class StringToNameParserTest {


	SignUpRequest signUpRequest = new SignUpRequest();

	@Mock
	OAuth2UserInfo oauth2UserInfo;

	UserEntity testUser = new UserEntity();
	String name1 = "user";
	String name2 = "test user";
	String name3 = "test big user";

	@Test
	void setUserNameFromRequest() {
		signUpRequest.setName("test user");
		UserEntity userEntity2 = UserEntity.builder().firstName("test").lastName("user").build();
		StringToNameParser.setUserNameFromRequest(signUpRequest,testUser);
		assertEquals(userEntity2, testUser);
	}

	@Test
	void testSetUserNameFromRequest() {
		UserEntity userEntity2 = UserEntity.builder().firstName("test").lastName("user").build();
		StringToNameParser.parseString(Arrays.asList(name2.split(" ")),testUser);
		assertEquals(userEntity2, testUser);
	}

	@Test
	void parseString() {
		UserEntity userEntity1 = UserEntity.builder().firstName("user").build();
		List<String> strings1 = Arrays.asList(name1.split(" "));
		StringToNameParser.parseString(strings1, testUser);
		assertEquals(userEntity1, testUser);
		testUser.setFirstName(null);

		UserEntity userEntity2 = UserEntity.builder().firstName("test").lastName("user").build();
		List<String> strings2 = Arrays.asList(name2.split(" "));
		StringToNameParser.parseString(strings2, testUser);
		assertEquals(userEntity2, testUser);
		testUser.setFirstName(null);
		testUser.setLastName(null);

		UserEntity userEntity3 = UserEntity.builder().firstName("test big user").build();
		List<String> strings3 = Arrays.asList(name3.split(" "));
		StringToNameParser.parseString(strings3, testUser);
		assertEquals(userEntity3, testUser);
	}
}
