package com.softserveinc.dokazovi.analytics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GoogleAnalyticsTest {
	@Mock
	private GoogleAnalytics googleAnalytics;

	@Test
	void getPostViewCount_isOk() {
		when(googleAnalytics.getPostViewCount("some")).thenReturn(1);
		googleAnalytics.getPostViewCount("some");
		verify(googleAnalytics, times(1)).getPostViewCount("some");
		assertEquals(1,
				googleAnalytics.getPostViewCount("some"));
	}

	@Test
	void getPostViewCount_NotFound_ThenReturn_0() {
		when(googleAnalytics.getPostViewCount("some")).thenReturn(0);

		assertEquals(0,
				googleAnalytics.getPostViewCount("some"));
	}
}
