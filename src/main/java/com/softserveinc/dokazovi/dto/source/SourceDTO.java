package com.softserveinc.dokazovi.dto.source;

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
public class SourceDTO {

	@NotNull(message = "Source id are required")
	private Integer id;

	@NotBlank(message = "Sources value cannot be empty")
	private String value;
}
