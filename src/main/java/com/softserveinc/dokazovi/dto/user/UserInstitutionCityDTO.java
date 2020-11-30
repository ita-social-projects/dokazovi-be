package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInstitutionCityDTO {

	private Integer id;

	private String name;
}
