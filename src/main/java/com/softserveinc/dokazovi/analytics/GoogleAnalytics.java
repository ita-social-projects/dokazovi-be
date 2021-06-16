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
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

import java.util.List;

/**
 * Provides access to the Google Analytics API using a service account.
 */
@Component
public class GoogleAnalytics {


	private static final String APPLICATION_NAME = "Google Analytics";
	private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static final String KEY_FILE_LOCATION = "client_secrets.json";
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

		String str = "{\n"
				+ "  \"type\": \"service_account\",\n"
				+ "  \"project_id\": \"dokazovi-315819\",\n"
				+ "  \"private_key_id\": \"784197cae9b59ccdd159d7635bf142da0f4a9bd0\",\n"
				+ "  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvQIBADANBgkqhkiG9w0BAQEFAA"
				+ "SCBKcwggSjAgEAAoIBAQDTTiHc7/WFNUyT\\nIwszD+JcbgrecYPXB5xS0Ja03RdcddwcjptjpcFVi"
				+ "9ajulAaDcSwJBKAs3+u3AZA\\nTlOjDx3WNpu+A7G7146PtyTsvyNza/JgECi0NKse8Smo7o/D1mczSY"
				+ "eyIkvuZZul\\neOkNKmQsCd2ZPqeVe+J6a521HJWFjR0+hEKKr+l8GqTFMx6ango6buNV5cVAkDLR\\ngG"
				+ "E+wEKgEOFwbnq0kw1hQRxTOCBtvQBysGwyFIL1h1FMPkv6zhXsegIwYvzSmBlQ\\ni8+2OigCzry5hLCjqh"
				+ "zTi+OSWM2xA5H/ZrwjE/kGNeeL0pQiYKgFYhnxYUz1uTts\\nGhD6i/gfAgMBAAECggEAZXymxfsYNGDrhN"
				+ "W1eNJduV/+52Sk2j+lBJ97aqWvpTBU\\nRgD2P+9qAoDV17BR7ZmESMcifd74ac7yT6tu/ydxAKQcvDABCsO"
				+ "WjI79dJm+2J5V\\nfG72ZcrQD13r0N+3e+s7iMX9nwBa8MarcVe3syOTlhUs8f/GNIUaOwr55XUzmT7T\\n/KO"
				+ "D6KPZKnN27wXWXDOq9oknS+51kuH6ErqopWdsPlG5p56QgmjKCvlCE6jn1Ndg\\nIaIE8WUoi1D3r9VAVJgFw6"
				+ "iOw+KqIG2KSkvqVHVbJoVEnrd3ZI49U313l+rpFYln\\ngRaAcEh+/N0dF3V/Cn3sfeOMaw18+G/J+dZI2/u/5"
				+ "QKBgQD5uC4KUoMsbV4yNwfp\\nD+BZq/6n5lPvPYijtq6BqMsXNWHli1QXnNZjKDGR3wp0YpXUjsRgtlotuWM0M"
				+ "w81\\nrG+REjH8dmmBMnlOJRSm4XfjIFSKnvbuEPdpF4dUpcT/OU+cHvY0vw4nFM9/ck6J\\nxVVA9kGFU8gRc"
				+ "+Bqx86yy13NswKBgQDYnp8//ULK+IUGltUUwd7iJWIBN/+etE0Z\\n3G/7HZqvIz45NhYOIH92z83rPOC7w0Nm"
				+ "yBwfbik+bhgb+DFVp93Tpv7qPLni7C/2\\n/OkDV8Ku19Pn/ML9b5IBDbIWpkFBiuWmJPf23Ckfm0tp1Nf5PD"
				+ "+QZI1tPiEm3btn\\nDKGWv1ut5QKBgQDyo8pgj0ITpgUz55LRG3MTznI3NC70NCcTZAr1w0vqthz0TEge\\nV"
				+ "W9ek4xuv3jKE4ygJ0Sa8DR5XzwMWVx6+5zAKhzVwTzMo32It7D0Yfn6f8yFkOY/\\n9c5gl9MQJa5fi5FWEiOK"
				+ "puWahbcEj83OQiaPY3fQk9K/WoeBWbn2snHnzwKBgC7d\\nqrCqd5/4QreZPIKgKzucQObomapZcfDta2DWF9ha"
				+ "dKTmGcF2sH0hGnkRk8GrzlJX\\naxY1vECgiyDKAsp4Zhrbn31IJCN/XCVAtJ0fiKRRV6xyWDGCyQDnWKdj0ux"
				+ "+Q489\\nolRLb65B5//+p7dpnIr95kdDUOLayCfUaMV3ozstAoGAIvsUz1qCswT7kHkY44Of\\nRM0GAFGr7zPy"
				+ "JvN5sJliveSFpIJd/Ryhj3f04dWaRn7jJhYXgoEYIkNlgHPx/FFB\\nk3A0VUtTEpK+Zj3JAE1JcH4B+io3rvBp"
				+ "ujiBcvhULoqD+axPDWVEY1Xv3GKZW1vJ\\njXJ05c30jJoVhfyZSFGjpFs=\\n-----END PRIVATE KEY-----\\n\",\n"
				+ "  \"client_email\": \"dokazovi@dokazovi-315819.iam.gserviceaccount.com\",\n"
				+ "  \"client_id\": \"116211317533348610360\",\n"
				+ "  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\n"
				+ "  \"token_uri\": \"https://oauth2.googleapis.com/token\",\n"
				+ "  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\n"
				+ "  \"client_x509_cert_url\": \"https://www.googleapis.com/robot/v1/metadata/x509/dokazovi"
				+ "%40dokazovi-315819.iam.gserviceaccount.com\"\n"
				+ "}";
		byte[] bytes = str.getBytes(StandardCharsets.UTF_8);

		GoogleCredential credential = GoogleCredential
				.fromStream(new ByteArrayInputStream(bytes), httpTransport, JSON_FACTORY)
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