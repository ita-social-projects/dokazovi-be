package com.softserveinc.dokazovi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

	private final Auth auth = new Auth();
	private final OAuth2 oauth2 = new OAuth2();

	public static class Auth {

		private String tokenSecret;
		@Value("${app.auth.tokenExpirationMsec}")
		private long tokenExpirationMsec;
		@Value("${app.auth.refreshTokenExpirationMsec}")
		private long refreshTokenExpirationMsec;

		public String getTokenSecret() {
			return tokenSecret;
		}

		public void setTokenSecret(String tokenSecret) {
			this.tokenSecret = tokenSecret;
		}

		public long getTokenExpirationMsec() {
			return tokenExpirationMsec;
		}

		public void setTokenExpirationMsec(long tokenExpirationMsec) {
			this.tokenExpirationMsec = tokenExpirationMsec;
		}

		public long getRefreshTokenExpirationMsec() {
			return refreshTokenExpirationMsec;
		}

		public void setRefreshTokenExpirationMsec(long refreshTokenExpirationMsec) {
			this.refreshTokenExpirationMsec = refreshTokenExpirationMsec;
		}

	}

	public static final class OAuth2 {

		private List<String> authorizedRedirectUris = new ArrayList<>();

		public List<String> getAuthorizedRedirectUris() {
			return authorizedRedirectUris;
		}

		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
			return this;
		}
	}

	public Auth getAuth() {
		return auth;
	}

	public OAuth2 getOauth2() {
		return oauth2;
	}
}

