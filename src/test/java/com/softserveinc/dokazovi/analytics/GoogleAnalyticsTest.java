package com.softserveinc.dokazovi.analytics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.powermock.core.classloader.annotations.PrepareForTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@PrepareForTest(GoogleAnalytics.class)
@ExtendWith(MockitoExtension.class)
public class GoogleAnalyticsTest {

	@InjectMocks
	private GoogleAnalytics googleAnalytics;

	@Test
	void getPostViewCount_ifViewsExist() {
		Integer postViewCount = googleAnalytics.getPostViewCount("/");
		assertTrue(postViewCount > 0);
	}

	@Test
	void getPostViewCount_ifViewsNoExist() {
		assertEquals(0, googleAnalytics.getPostViewCount("some"));
	}
}
