package com.softserveinc.dokazovi.dto.direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * The DirectionDTO is responsible for passing
 * direction ata from server to the client.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDTO {

	@NotNull(message = "Direction id are required")
	private Integer id;

	@NotBlank(message = "Direction name cannot be empty")
	private String name;

	@NotBlank(message = "Direction label cannot be empty")
	private String label;

	private String color;

	private Boolean hasDoctors;

	private Boolean hasPosts;
}
