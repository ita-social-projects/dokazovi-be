package com.softserveinc.dokazovi.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExpertPreviewDTO {

	private Integer id;

	private String firstName;

	private String lastName;

	private UserDirectionDTO direction;

	private ExpertInstitutionDTO institution;

	private String qualification;

	private LatestExpertPostDTO lastAddedPost;

}
