package com.softserveinc.dokazovi.dto.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

	private String email;

	private String password;

	private String firstName;

	private String lastName;

	private String qualification;

	private Integer mainInstitutionId;

	private String avatar;

	private String bio;

	private Set<String> socialNetwork;
}