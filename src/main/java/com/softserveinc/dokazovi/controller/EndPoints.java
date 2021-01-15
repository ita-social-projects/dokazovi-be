package com.softserveinc.dokazovi.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndPoints {

	public static final String VERSION = "/version";
	public static final String POST = "/post";
	public static final String POST_TYPE = "/type";
	public static final String POST_LATEST = "/latest";
	public static final String POST_IMPORTANT = "/important";
	public static final String POST_GET_POST_BY_ID = "/{postId}";
	public static final String USER = "/user";
	public static final String USER_RANDOM_EXPERTS = "/random-experts";
	public static final String USER_ALL_EXPERTS = "/all-experts";
	public static final String USER_GET_USER_BY_ID = "/{userId}";
	public static final String TAG = "/tag";
	public static final String TAG_FIND_BY_VALUE = "/find-by-value";
	public static final String POST_LATEST_BY_DIRECTION = "/latest-by-direction";
	public static final String POST_LATEST_BY_EXPERT = "/latest-by-expert";
	public static final String DIRECTION = "/direction";
	public static final String REGION = "/region";
	public static final String AUTH = "/auth";
	public static final String AUTH_VERIFICATION = "/verification";
	public static final String AUTH_LOGIN = "/login";
	public static final String AUTH_SIGNUP = "/signup";
}
