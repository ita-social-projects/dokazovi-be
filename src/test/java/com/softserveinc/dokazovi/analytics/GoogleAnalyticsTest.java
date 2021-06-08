package com.softserveinc.dokazovi.analytics;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;

import com.google.api.services.analytics.model.GaData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.FileInputStream;
import java.io.IOException;

import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;

@PrepareForTest(GoogleAnalytics.class)
@ExtendWith(MockitoExtension.class)
public class GoogleAnalyticsTest {

	@InjectMocks
	private GoogleAnalytics googleAnalytics;

	@Mock
	private GoogleAnalytics googleAnalyticsMock;


	@Test
	void getPostViewCount() {

		assertEquals(0, googleAnalytics.getPostViewCount("some"));
		assertEquals(4, googleAnalytics.getPostViewCount("/experts"));
	}


	@Test
	void getPostViewCount_WhenWrong_ThrowException()  {

		PowerMockito.stub(PowerMockito
				.method(GoogleCredential.class, "fromStream", FileInputStream.class))
				.toThrow(new IOException());

		assertEquals(0, googleAnalytics.getPostViewCount("some"));


	}
}
