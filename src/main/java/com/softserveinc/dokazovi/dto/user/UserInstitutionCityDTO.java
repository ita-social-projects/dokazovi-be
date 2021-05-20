package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * The UserInstitutionCityDTO is responsible for passing latest user institution city data from server to the client.
 */

@Data
@Builder
public class UserInstitutionCityDTO {

	private Integer id;

	private String name;
}
