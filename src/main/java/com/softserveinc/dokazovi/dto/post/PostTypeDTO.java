package com.softserveinc.dokazovi.dto.post;

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
public class PostTypeDTO {

	@NotNull(message = "PostType id are required")
	private Integer id;

	@NotBlank(message = "PostType name cannot be empty")
	private String name;
}
