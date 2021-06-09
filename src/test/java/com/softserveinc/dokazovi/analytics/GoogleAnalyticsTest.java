package com.softserveinc.dokazovi.analytics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;

import org.mockito.junit.jupiter.MockitoExtension;

import org.powermock.core.classloader.annotations.PrepareForTest;

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
}
