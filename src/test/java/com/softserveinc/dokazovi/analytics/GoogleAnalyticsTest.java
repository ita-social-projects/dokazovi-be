package com.softserveinc.dokazovi.analytics;


import com.google.api.services.analytics.Analytics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import org.powermock.core.classloader.annotations.PrepareForTest;


import static org.junit.jupiter.api.Assertions.assertEquals;


@PrepareForTest(GoogleAnalytics.class)
@ExtendWith(MockitoExtension.class)
public class GoogleAnalyticsTest {

	@InjectMocks
	private GoogleAnalytics googleAnalytics;
	@Spy
	private GoogleAnalytics googleAnalyticsSpy;
	@Mock
	private Analytics analyticsMock;
	@Mock
	private Analytics.Data analyticsDataMock;
	@Mock
	private Analytics.Data.Ga analyticsDataGaMock;

	@Test
	void getPostViewCount() {

		assertEquals(0, googleAnalytics.getPostViewCount("some"));
		assertEquals(42, googleAnalytics.getPostViewCount("/experts"));
	}

	/*@Test
	void getPostViewCount_WhenWrong_ThrowException() throws IOException, GeneralSecurityException {
		String APPLICATION_NAME = "Google Analytics";
		JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
		String KEY_FILE_LOCATION = "src/main/resources/client_secrets.json";
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		HttpTransport httpTransportSpy = spy(httpTransport);

		GoogleCredential credential = GoogleCredential
				.fromStream(new FileInputStream(KEY_FILE_LOCATION))
				.createScoped(AnalyticsScopes.all());

		Analytics analyticsIns = new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();

		Analytics.Data.Ga analyticsDataGaIns = analyticsIns.data().ga();


		Analytics.Data.Ga analyticsDataGaSpy = spy(analyticsDataGaIns);

		when(httpTransportSpy)
				.thenThrow(new GeneralSecurityException());

		*//*Analytics.Data.Ga.Get today1 = analyticsDataGaSpy
				.get("ga:243645435", "2021-03-22", "today", "ga:uniquePageviews");*//*
		googleAnalyticsSpy.getPostViewCount("some");*//**//*
		 assertEquals(null, googleAnalytics.getPostViewCount("some"));
	}*/
}
