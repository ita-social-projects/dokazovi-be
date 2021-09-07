package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.analytics.GoogleAnalytics;
import com.softserveinc.dokazovi.service.ViewCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewCountServiceImpl implements ViewCountService {

	private final GoogleAnalytics analytics;

	@Override
	@Cacheable({"viewCount"})
	public Integer fetchViewCount(String url) {
		return analytics.getPostViewCount(url);
	}
}
