package com.softserveinc.dokazovi.dto.region;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegionDTO {

	private Integer id;
	private String name;
}
