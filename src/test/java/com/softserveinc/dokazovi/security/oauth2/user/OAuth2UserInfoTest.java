package com.softserveinc.dokazovi.security.oauth2.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ExtendWith(MockitoExtension.class)
class OAuth2UserInfoTest {

    private Map<String, Object> dataObj;
    private FacebookOAuth2UserInfo userFacebook;


    @BeforeEach
    public void init() {
        dataObj = new ConcurrentHashMap<>(Map.of("url", "image"));
        Map<String, Object> pictureObj = new ConcurrentHashMap<>(Map.of("data", dataObj));
        Map<String, Object> attributes = new ConcurrentHashMap<>(Map.of(
                "id", 1,
                "name", "admin",
                "email", "admin@mail.com",
                "picture", pictureObj
        ));
        userFacebook = new FacebookOAuth2UserInfo(attributes);
    }

    @Test
    void getEmail() {
        Assertions.assertEquals("admin@mail.com", userFacebook.getEmail());
    }

    @Test
    void getName() {
        Assertions.assertEquals("admin", userFacebook.getName());
    }

    @Test
    void getImageUrl() {
        Assertions.assertEquals("image", userFacebook.getImageUrl());
    }

    @Test
    void getUserEmptyPicture() {
        dataObj.remove("url");
        Assertions.assertNull(userFacebook.getImageUrl());
    }

    @Test
    void getAttributes() {
        Assertions.assertEquals(4, userFacebook.getAttributes().size());
    }
}