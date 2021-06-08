package com.softserveinc.dokazovi.analytics;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@PrepareForTest(GoogleAnalytics.class)
@ExtendWith(MockitoExtension.class)
public class GoogleAnalyticsTest {

	@InjectMocks
	private GoogleAnalytics googleAnalytics;

	@Test
	void getPostViewCount() {

		assertEquals(0, googleAnalytics.getPostViewCount("some"));
		assertEquals(4, googleAnalytics.getPostViewCount("/experts"));
	}


	@Test
	void getPostViewCount_WhenWrong_ThrowException() {

		PowerMockito.stub(PowerMockito
				.method(GoogleCredential.class, "fromStream", FileInputStream.class))
				.toThrow(new IOException());

		assertEquals(0, googleAnalytics.getPostViewCount("some"));


	}
}
