package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UserDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private String email;

	private String qualification;

	private String phone;

	private String avatar;

	private String bio;

	private UserDirectionDTO mainDirection;

	private Set<UserDirectionDTO> directions;

	private UserInstitutionDTO mainInstitution;

	private Set<UserInstitutionDTO> institutions;

	private LatestUserPostDTO lastAddedPost;

}
