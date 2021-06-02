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

import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * A simple example of how to access the Google Analytics API using a service account.
 */
@Component
public class GoogleAnalytics {

	private static final String APPLICATION_NAME = "Hello Analytics";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String KEY_FILE_LOCATION = "C:\\Users\\Masha\\IdeaProjects\\dokazovi-be\\src\\main\\java\\com\\softserveinc\\dokazovi\\analytics\\client_secrets.json";

	public Integer getPostViewCount(String url) throws GeneralSecurityException, IOException {

		Analytics analytics = initializeAnalytic();

		String profile = getFirstProfileId(analytics);

		return Integer.parseInt(getResults(analytics, profile, url).getRows().get(0).get(1));
	}

	/**
	 * Initializes an Analytics service object.
	 *
	 * @return An authorized Analytics service object.
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private Analytics initializeAnalytic() throws GeneralSecurityException, IOException {

		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = GoogleCredential
				.fromStream(new FileInputStream(KEY_FILE_LOCATION))
				.createScoped(AnalyticsScopes.all());

		// Construct the Analytics service object.
		return new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	private String getFirstProfileId(Analytics analytics) throws IOException {
		// Get the first view (profile) ID for the authorized user.
		String profileId = null;

		// Query for the list of all accounts associated with the service account.
		Accounts accounts = analytics.management().accounts().list().execute();

		if (accounts.getItems().isEmpty()) {
			System.err.println("No accounts found");
		} else {
			String firstAccountId = accounts.getItems().get(0).getId();

			// Query for the list of properties associated with the first account.
			Webproperties properties = analytics.management().webproperties()
					.list(firstAccountId).execute();

			if (properties.getItems().isEmpty()) {
				System.err.println("No Webproperties found");
			} else {
				String firstWebpropertyId = properties.getItems().get(0).getId();

				// Query for the list views (profiles) associated with the property.
				Profiles profiles = analytics.management().profiles()
						.list(firstAccountId, firstWebpropertyId).execute();

				if (profiles.getItems().isEmpty()) {
					System.err.println("No views (profiles) found");
				} else {
					// Return the first (view) profile associated with the property.
					profileId = profiles.getItems().get(0).getId();
				}
			}
		}
		return profileId;
	}

	private GaData getResults(Analytics analytics, String profileId, String url) throws IOException {
		// Query the Core Reporting API for the number of sessions
		// in the past seven days.

		return analytics
				.data()
				.ga()
				.get("ga:" + profileId, "2021-03-22", "today", "ga:pageviews")
				.setDimensions("ga:pagePath")
				.setFilters("ga:pagePath==" + url)
				.execute();
	}
}
