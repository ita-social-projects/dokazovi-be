package com.softserveinc.dokazovi.dto;

import lombok.Data;

@Data
public class AuthorDto {

	private Integer id;

	private String firstName;

	private String lastName;

	InstitutionDto institution;

}
