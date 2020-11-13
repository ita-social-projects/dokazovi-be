package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpertPreviewDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private UserDirectionDTO mainDirection;

	private ExpertInstitutionDTO mainInstitution;

	private String qualification;

	private LatestExpertPostDTO lastAddedPost;

}
