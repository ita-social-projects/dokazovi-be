package com.softserveinc.dokazovi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Data
@EqualsAndHashCode
public class UserSearchCriteria {

	@ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
	private Set<Integer> directions;

	private @ApiParam(value = "Multiple comma-separated region IDs, e.g. ?regions=1,2,3,4", type = "string")
	Set<Integer> regions;

	@ApiParam(value = "User name", type = "string")
	private String userName = "";


	public List<String> getUserNameList() {
		String PATTERN_NAME = "[A-Z,А-Я,a-z,а-я\\s\\-]{1,}";

		String name = this.userName.trim();

		if (!Pattern.matches(PATTERN_NAME, name)) {
			return new ArrayList<>();
		}
		List<String> result = Arrays.asList(name.split(" "));
		Collections.sort(result, Collections.reverseOrder());

		return result;
	}

	public boolean hasRegions() {
		return this.regions != null && !this.regions.isEmpty();
	}

	public boolean hasDirections() {
		return this.directions != null && !this.directions.isEmpty();
	}

	public boolean hasName() {
		return this.userName.length() > 0;
	}
}
