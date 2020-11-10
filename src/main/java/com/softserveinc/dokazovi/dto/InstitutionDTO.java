package com.softserveinc.dokazovi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstitutionDTO {

	private Integer id;

	private RegionDTO region;

	private String name;

	private String address;

}
