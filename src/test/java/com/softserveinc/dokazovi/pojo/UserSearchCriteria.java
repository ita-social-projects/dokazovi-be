package com.softserveinc.dokazovi.pojo;

import io.swagger.annotations.ApiParam;

import java.util.Objects;
import java.util.Set;

public class UserSearchCriteria {

	@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
	Set<Integer> directions;

	@ApiParam(value = "Multiple comma-separated region IDs, e.g. ?regions=1,2,3,4", type = "string")
	Set<Integer> regions;

	@ApiParam(value = "User name", type = "string")
	String userName = "";

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
		this.userName = userName;
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
