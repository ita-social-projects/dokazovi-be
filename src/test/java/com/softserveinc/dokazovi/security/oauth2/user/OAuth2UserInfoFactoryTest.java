package com.softserveinc.dokazovi.security.oauth2.user;

import com.softserveinc.dokazovi.exception.OAuth2AuthenticationProcessingException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OAuth2UserInfoFactoryTest {

	@Test
	void getOAuth2GoogleUserInfo() {
		 Map<String, Object> map = Map.of(
				"sub", "1",
				"name", "admin",
				"email", "admin@mail.com",
				"picture", "pictureUrl");

		OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo("google", map);
		assertEquals("1", userInfo.getId());
		assertEquals("admin", userInfo.getName());
		assertEquals("admin@mail.com", userInfo.getEmail());
		assertEquals("pictureUrl", userInfo.getImageUrl());
	}

	@Test
	void getOAuth2FacebookUserInfo() {
		Map<String, Object> map = Map.of(
				"id", "1",
				"name", "admin",
				"email", "admin@mail.com",
				"picture", Map.ofEntries(Map.entry("data",
						Map.ofEntries(Map.entry("url", "imageUrl"))
				)));

		OAuth2UserInfo userInfo = OAuth2UserInfoFactory.getOAuth2UserInfo("facebook", map);
		assertEquals("1", userInfo.getId());
		assertEquals("admin", userInfo.getName());
		assertEquals("admin@mail.com", userInfo.getEmail());
		assertEquals("imageUrl", userInfo.getImageUrl());
	}

	@Test
	void getOAuth2UserInfoInvalid() {
		assertThrows(OAuth2AuthenticationProcessingException.class,
				() -> OAuth2UserInfoFactory.getOAuth2UserInfo("foobar", Map.of()));
	}
}