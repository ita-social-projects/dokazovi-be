package com.softserveinc.dokazovi.analytics;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.AnalyticsScopes;
import com.google.api.services.analytics.model.Accounts;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.Profiles;
import com.google.api.services.analytics.model.Webproperties;

import com.softserveinc.dokazovi.security.RestAuthenticationEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

import java.util.List;

/**
 * Provides access to the Google Analytics API using a service account.
 */
@Component
public class GoogleAnalytics {

	@Value("${analytics.creds}")
	private String googleCredsFromJSON;
	private static final String APPLICATION_NAME = "Google Analytics";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);

	public Integer getPostViewCount(String url) {

		List<List<String>> rows = null;
		try {
			Analytics analytics = initializeAnalytic();

			String profile = getFirstProfileId(analytics);

			rows = getResults(analytics, profile, url).getRows();

		} catch (IOException ie) {
			logger.error("IOException occurred", ie);
		}

		return rows == null ? 0 : Integer.parseInt(rows.get(0).get(1));
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

	private String getFirstProfileId(Analytics analytics) throws IOException {
		/**
		 * Get the first view (profile) ID for the authorized user.
		 */
		String profileId = null;

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

	private GaData getResults(Analytics analytics, String profileId, String url) throws IOException {
		/** Query the Core Reporting API for the number of sessions
		 * from start Date until today.
		 */
		return analytics
				.data()
				.ga()
				.get("ga:" + profileId, "2021-03-22", "today", "ga:uniquePageviews")
				.setDimensions("ga:pagePath")
				.setFilters("ga:pagePath==" + url)
				.execute();
	}
}