package com.softserveinc.dokazovi.pojo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * The UserSearchCriteria has a list of fields which match with
 * request parameters that will be bound from the HTTP request,
 * standard getters/setters, and a no-argument constructor..
 */

@Data
@EqualsAndHashCode
public class UserSearchCriteria {

    @ApiParam(value = "Multiple comma-separated direction IDs, e.g. ?directions=1,2,3,4", type = "string")
    private Set<Integer> directions;

    private @ApiParam(value = "Multiple comma-separated region IDs, e.g. ?regions=1,2,3,4", type = "string")
    Set<Integer> regions;

    @ApiParam(value = "User name", type = "string")
    private String userName = "";

    /**
     * Validates the name according to regular expression.
     * Returns user name or first name and last name depending on
     * whether the userName contains any spaces after trimming.
     */
    @ApiModelProperty(hidden = true)
    public List<String> getUserNameList() {
        String patternName = "[A-ZА-Яa-zа-яЇїІіЄєҐґ\\'\\s\\-]{1,}";

        String name = this.userName.trim();

        if (!Pattern.matches(patternName, name)) {
            return new ArrayList<>();
        }

        List<String> result = Arrays.asList(name.split(" "));
        result.sort(Collections.reverseOrder());

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
