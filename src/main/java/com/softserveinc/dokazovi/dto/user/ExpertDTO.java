package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class ExpertDTO {

	private Integer id;

	private String avatar;

	private String firstName;

	private String lastName;

	private String qualification;

	private UserDirectionDTO mainDirection;

	private Set<UserDirectionDTO> directions;

	private ExpertInstitutionDTO mainInstitution;

	private String phone;

	private String email;

	private String bio;
}
