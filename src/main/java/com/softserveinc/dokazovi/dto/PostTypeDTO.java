package com.softserveinc.dokazovi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostTypeDTO {

	private Integer id;

	private String name;
}
