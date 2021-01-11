package com.softserveinc.dokazovi.dto.direction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionDTO {
	private Integer id;
	private String name;
	private String label;
	private String color;
}
