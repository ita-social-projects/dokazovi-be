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

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

/**
 * A simple example of how to access the Google Analytics API using a service account.
 */
public class HelloAnalytics {

	private static final String APPLICATION_NAME = "Hello Analytics";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String KEY_FILE_LOCATION = "";

	public static void main(String[] args) {
		try {
			Analytics analytics = initializeAnalytic();

			String profile = getFirstProfileId(analytics);
			System.out.println("First Profile Id: " + profile);
			printResults(getResults(analytics, profile));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes an Analytics service object.
	 *
	 * @return An authorized Analytics service object.
	 * @throws IOException
	 * @throws GeneralSecurityException
	 */
	private static Analytics initializeAnalytic() throws GeneralSecurityException, IOException {

		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		GoogleCredential credential = GoogleCredential
				.fromStream(new FileInputStream(KEY_FILE_LOCATION))
				.createScoped(AnalyticsScopes.all());

		// Construct the Analytics service object.
		return new Analytics.Builder(httpTransport, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME).build();
	}

	private static String getFirstProfileId(Analytics analytics) throws IOException {
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

	private static GaData getResults(Analytics analytics, String profileId) throws IOException {
		// Query the Core Reporting API for the number of sessions
		// in the past seven days.

		GaData today = analytics
				.data()
				.ga()
				.get("ga:" + profileId, "2021-03-22", "today", "ga:pageviews")
				.setDimensions("ga:pagePath")
				.setFilters("ga:pagePath==/experts/14")
				.execute();

		List<List<String>> rows = today.getRows();

		return today;
	}

	private static void printResults(GaData results) {
		// Parse the response from the Core Reporting API for
		// the profile name and number of sessions.
		if (results != null && !results.getRows().isEmpty()) {
			System.out.println("View (Profile) Name: "
					+ results.getProfileInfo().getProfileName());

			//System.out.println(results.getDataTable());
			Map<String, String> rows = results.getTotalsForAllResults();
			System.out.println(rows);
			/*for (String> list : rows){
				for (String str : list){
					System.out.println(str);
				}
			}*/
			System.out.println("Total Sessions: " + rows);
		} else {
			System.out.println("No results found");
		}
	}
}
