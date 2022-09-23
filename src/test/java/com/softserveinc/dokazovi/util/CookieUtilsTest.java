package com.softserveinc.dokazovi.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CookieUtilsTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;


    @Test
    void getCookie() {
        Cookie cookie = new Cookie("test", "test");
        Cookie[] cookies = new Cookie[]{cookie};
        when(request.getCookies()).thenReturn(cookies);
        Optional<Cookie> expectedCookie = CookieUtils.getCookie(request, "test");
        expectedCookie.ifPresent(value -> assertEquals(cookie, value));

    }

    @Test
    void deleteCookie() {
        Cookie cookie = new Cookie("test", "test");
        Cookie[] cookies = new Cookie[]{cookie};
        when(request.getCookies()).thenReturn(cookies);
        CookieUtils.addCookie(response, "test", "test", 10000);
        CookieUtils.deleteCookie(request, response, "test");
        String expectedValue = "";
        Optional<Cookie> actualCookie = CookieUtils.getCookie(request, "test");
        actualCookie.ifPresent(value -> assertEquals(expectedValue, value.getValue()));
    }

}
