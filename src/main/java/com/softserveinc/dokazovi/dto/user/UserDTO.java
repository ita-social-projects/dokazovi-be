package com.softserveinc.dokazovi.dto.user;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

/**
 * The User DTO is responsible for passing user data from the server to the client.
 */

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

	private String region;

	private String city;

	private Set<String> socialNetworks;

	private Set<DirectionDTO> directions;

	private UserInstitutionDTO mainInstitution;

	private Set<UserInstitutionDTO> institutions;

	private LatestUserPostDTO lastAddedPost;

	private Timestamp createdAt;

	private Timestamp editedAt;
}
