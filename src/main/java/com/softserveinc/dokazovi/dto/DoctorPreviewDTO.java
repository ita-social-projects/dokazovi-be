package com.softserveinc.dokazovi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorPreviewDTO {

	private Integer doctorId;

	private String name;

	private String qualification;

	private UserDirectionDTO direction;

	private InstitutionDTO institution;

	private LatestExpertPostDTO lastAddedPost;

}
