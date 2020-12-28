package com.softserveinc.dokazovi.dto.direction;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirectionDTO {
	private Integer id;
	private String name;
	private String label;
	private String color;
}
