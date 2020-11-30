package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInstitutionDTO {

	private Integer id;

	private UserInstitutionCityDTO city;

	private String name;

}
