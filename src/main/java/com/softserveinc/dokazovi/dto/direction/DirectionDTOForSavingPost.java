package com.softserveinc.dokazovi.dto.direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDTOForSavingPost {
	@NotNull(message = "Direction id are required")
	private Integer id;
}
