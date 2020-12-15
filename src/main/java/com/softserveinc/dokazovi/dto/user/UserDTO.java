package com.softserveinc.dokazovi.dto.user;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
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

	private DirectionDTO mainDirection;

	private Set<DirectionDTO> directions;

	private UserInstitutionDTO mainInstitution;

	private Set<UserInstitutionDTO> institutions;

	private LatestUserPostDTO lastAddedPost;

}
