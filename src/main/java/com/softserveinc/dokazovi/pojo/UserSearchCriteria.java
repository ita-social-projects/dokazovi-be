package com.softserveinc.dokazovi.pojo;

import io.swagger.annotations.ApiParam;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class UserSearchCriteria {

	public static final String PATTERN_NAME = "[A-Z,А-Я,a-z,а-я\\s\\-]{1,}";

	@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
	private Set<Integer> directions;

	private @ApiParam(value = "Multiple comma-separated region IDs, e.g. ?regions=1,2,3,4", type = "string")
	Set<Integer> regions;

	@ApiParam(value = "User name", type = "string")
	private String userName = "";

	public Set<Integer> getDirections() {
		return directions;
	}

	public void setDirections(Set<Integer> directions) {
		this.directions = directions;
	}

	public Set<Integer> getRegions() {
		return regions;
	}

	public void setRegions(Set<Integer> regions) {
		this.regions = regions;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		userName = userName.trim();

		if (!Pattern.matches(PATTERN_NAME, userName)) {
			throw new IllegalArgumentException("Wrong Name");
		}
		this.userName = userName;
	}

	public void setUserNameForTesting(String userName) {
		this.userName = userName;
	}

	public boolean isEmpty(Set set) {
		return set.isEmpty() || set == null;
	}

	public boolean isEmpty(String str) {
		return str.isEmpty() || str == null;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserSearchCriteria that = (UserSearchCriteria) o;
		return Objects.equals(directions, that.directions) && Objects.equals(regions, that.regions)
				&& Objects.equals(userName, that.userName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(directions, regions, userName);
	}
}
