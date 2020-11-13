package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpertInstitutionDTO {

	private Integer id;

	private ExpertInstitutionCityDTO city;

	private String name;

}
