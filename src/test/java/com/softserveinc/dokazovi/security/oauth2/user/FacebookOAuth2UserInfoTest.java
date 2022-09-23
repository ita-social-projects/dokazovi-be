package com.softserveinc.dokazovi.security.oauth2.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
class FacebookOAuth2UserInfoTest {

    private FacebookOAuth2UserInfo facebookOAuth2UserInfoUnderTest;

    @BeforeEach
    void init() {
        facebookOAuth2UserInfoUnderTest = new FacebookOAuth2UserInfo(Map.of(
                "id", "1",
                "name", "admin",
                "email", "admin@mail.com",
                "picture", Map.of("data", Map.of("url", "imageUrl"))
        ));
    }

    @Test
    void getId() {
        Assertions.assertEquals("1", facebookOAuth2UserInfoUnderTest.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals("admin", facebookOAuth2UserInfoUnderTest.getName());
    }

    @Test
    void getEmail() {
        Assertions.assertEquals("admin@mail.com", facebookOAuth2UserInfoUnderTest.getEmail());
    }

    @Test
    void getImageUrl() {
        Assertions.assertEquals("imageUrl", facebookOAuth2UserInfoUnderTest.getImageUrl());
    }

    @Test
    void getAttributes() {
        Assertions.assertEquals(4, facebookOAuth2UserInfoUnderTest.getAttributes().size());
    }
}