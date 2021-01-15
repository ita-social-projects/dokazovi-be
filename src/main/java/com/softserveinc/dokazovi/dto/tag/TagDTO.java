package com.softserveinc.dokazovi.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDTO {

	@NotNull(message = "Tag id are required")
	private Integer id;

	@NotBlank(message = "Tag cannot be empty")
	private String tag;
}
