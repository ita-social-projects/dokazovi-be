package com.softserveinc.dokazovi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	InstitutionDTO institution;

}
