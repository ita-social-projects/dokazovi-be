package com.softserveinc.dokazovi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPreviewDTO {

	private Integer id;

	private String avatarUrl;

	private String firstName;

	private String lastName;

	private DirectionDTO direction;

	private InstitutionDTO institution;

	private String qualification;

	private LastAddedPostDTO lastAddedPost;

}
