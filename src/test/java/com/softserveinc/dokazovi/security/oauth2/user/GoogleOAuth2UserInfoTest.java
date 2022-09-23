package com.softserveinc.dokazovi.security.oauth2.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class GoogleOAuth2UserInfoTest {

    private GoogleOAuth2UserInfo googleOAuth2UserInfo;

    @BeforeEach
    public void init() {
        Map<String, Object> attributes = new HashMap<>(Map.of(
                "sub", "1",
                "name", "admin",
                "email", "admin@mail.com",
                "picture", "picture url"
        ));
        googleOAuth2UserInfo = new GoogleOAuth2UserInfo(attributes);
    }

    @Test
    public void getId() throws Exception {
        Assertions.assertEquals("1", googleOAuth2UserInfo.getId());
    }

    @Test
    void getName() {
        Assertions.assertEquals("admin", googleOAuth2UserInfo.getName());
    }

    @Test
    void getEmail() {
        Assertions.assertEquals("admin@mail.com", googleOAuth2UserInfo.getEmail());
    }

    @Test
    void getImageUrl() {
        Assertions.assertEquals("picture url", googleOAuth2UserInfo.getImageUrl());
    }

    @Test
    void getAttributes() {
        Assertions.assertEquals(4, googleOAuth2UserInfo.getAttributes().size());
    }
}