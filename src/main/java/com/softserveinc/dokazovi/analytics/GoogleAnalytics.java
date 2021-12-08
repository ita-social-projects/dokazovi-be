package com.softserveinc.dokazovi.analytics;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

import com.softserveinc.dokazovi.security.RestAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Provides access to the Google Analytics API using a service account.
 */
@Component
public class GoogleAnalytics {

	@Value("${analytics.creds}")
	private String googleCredsFromJSON;

	@Value("${analytics.profile:none}")
	private String analyticsProfileId;

	private String profileId = null;

	private static final String APPLICATION_NAME = "Google Analytics";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	@Cacheable({"viewCount"})
	public Integer getPostViewCount(String url) {

		List<List<String>> rows = null;
		try {
			Analytics analytics = initializeAnalytic();

			String profile = null;

			if (!analyticsProfileId.equals("none")) {
				profile = getProfileIdByConfig();
			} else {
				profile = getFirstProfileId(analytics);
			}

			rows = getResults(analytics, profile, url).execute().getRows();

		} catch (IOException ie) {
			logger.error("IOException occurred", ie);
		}

		return rows == null ? 0 : Integer.parseInt(rows.get(0).get(1));
	}

	public Map<Integer, Integer> getAllPostsViewCount() {
		List<List<String>> rows;
		Map<Integer, Integer> map = null;
		try {
			Analytics analytics = initializeAnalytic();

			String profile = null;

			if (!analyticsProfileId.equals("none")) {
				profile = getProfileIdByConfig();
			} else {
				profile = getFirstProfileId(analytics);
			}

			rows = getResults(analytics, profile).execute().getRows();
			map = rows.stream()
					.filter(e -> Pattern.matches("(/posts/)(\\d+)", e.get(0)))
					.peek(e -> {
						Pattern pattern1 = Pattern.compile("(/posts/)(\\d+)");
						Matcher matcher = pattern1.matcher(e.get(0));
						while (matcher.find()) {
							e.add(0, matcher.group(2));
						}
						e.remove(1);
					})
					.collect(Collectors.toMap(k -> Integer.parseInt(k.get(0)), v -> Integer.parseInt(v.get(1))));

		} catch (IOException ie) {
			logger.error("IOException occurred", ie);
		}
		return Optional.ofNullable(map).orElse(new HashMap<>());
	}

	/**
	 * Initializes an Analytics service object.
	 *
	 * @return An authorized Analytics service object.
	 */
	private Analytics initializeAnalytic() throws IOException {
		HttpTransport httpTransport = null;
		try {
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		} catch (GeneralSecurityException e) {
			logger.error("GeneralSecurityException occurred. HttpTransport is failed", e);
		}

		GoogleCredential credential = GoogleCredential
				.fromStream(new ByteArrayInputStream(googleCredsFromJSON
						.getBytes()), httpTransport, JSON_FACTORY)
				.createScoped(AnalyticsScopes.all());

		/**
		 *  Construct the Analytics service object.
		 */
		return new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	private String getProfileIdByConfig() {
		if (profileId != null) {
			return profileId;
		}

		logger.info("We have our Google Analytics profile ID passed directly. Will use that.");

		profileId = analyticsProfileId;

		return profileId;
	}

	private String getFirstProfileId(Analytics analytics) throws IOException {
		if (profileId != null) {
			return profileId;
		}

		/**
		 * Query for the list of all accounts associated with the service account.
		 */
		Accounts accounts = analytics.management().accounts().list().execute();

		if (accounts.getItems().isEmpty()) {
			logger.error("No accounts found ");
		} else {
			String firstAccountId = accounts.getItems().get(0).getId();
			/**
			 * Query for the list of properties associated with the first account.
			 */
			Webproperties properties = analytics.management().webproperties()
					.list(firstAccountId).execute();

			if (properties.getItems().isEmpty()) {
				logger.error("No Webproperties found");
			} else {
				String firstWebpropertyId = properties.getItems().get(0).getId();

				/**
				 * Query for the list views (profiles) associated with the property.
				 */
				Profiles profiles = analytics.management().profiles()
						.list(firstAccountId, firstWebpropertyId).execute();

				if (profiles.getItems().isEmpty()) {
					logger.error("No views (profiles) found");
				} else {
					/**
					 * Return the first (view) profile associated with the property.
					 */
					profileId = profiles.getItems().get(0).getId();
				}
			}
		}
		return profileId;
	}

	private Analytics.Data.Ga.Get getResults(Analytics analytics, String profileId, String url) throws IOException {
		/** Query the Core Reporting API for the number of sessions
		 * from start Date until today.
		 */
		return analytics
				.data()
				.ga()
				.get("ga:" + profileId, "2021-03-22", "today", "ga:uniquePageviews")
				.setDimensions("ga:pagePath")
				.setFilters("ga:pagePath==" + url);

	}

	private Analytics.Data.Ga.Get getResults(Analytics analytics, String profileId) throws IOException {
		/** Query the Core Reporting API for the number of sessions
		 * from start Date until today.
		 */
		return analytics
				.data()
				.ga()
				.get("ga:" + profileId, "2021-03-22", "today", "ga:uniquePageviews")
				.setDimensions("ga:pagePath");

	}
}
