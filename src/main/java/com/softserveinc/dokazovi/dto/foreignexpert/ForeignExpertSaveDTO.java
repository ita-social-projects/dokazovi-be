package com.softserveinc.dokazovi.dto.foreignexpert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ForeignExpertSaveDTO {
	private Integer id;

	@NotBlank(message = "Full name must be present")
	private String fullName;

	private String bio;
}
