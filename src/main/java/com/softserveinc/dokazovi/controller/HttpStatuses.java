package com.softserveinc.dokazovi.controller;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Http statuses for setting them to Api responses.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HttpStatuses {

	public static final String OK = "OK";
	public static final String CREATED = "Created";
	public static final String BAD_REQUEST = "Bad Request";
	public static final String UNAUTHORIZED = "Unauthorized";
	public static final String FORBIDDEN = "Forbidden";
	public static final String NOT_FOUND = "Not Found";
	public static final String SEE_OTHER = "SEE_OTHER";
	public static final String INTERNAL_SERVER_ERROR = "INTERNAL_SERVER_ERROR";

}
