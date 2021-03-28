package com.softserveinc.dokazovi.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUserDTO {

	private Integer id;
	private String firstName;
	private String lastName;
	private String avatar;
	private PostUserInstitutionDTO mainInstitution;
}
