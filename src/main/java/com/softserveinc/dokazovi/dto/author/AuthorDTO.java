package com.softserveinc.dokazovi.dto.author;

import com.softserveinc.dokazovi.dto.user.UserInstitutionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDTO {

	private Integer id;

	private String email;

	private String firstName;

	private String lastName;

	private String qualification;

	private UserInstitutionDTO mainInstitution;

	private String avatar;

	private String bio;

	private String socialNetwork;
}
