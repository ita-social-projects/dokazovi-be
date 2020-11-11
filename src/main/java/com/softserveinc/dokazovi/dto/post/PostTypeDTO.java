package com.softserveinc.dokazovi.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostTypeDTO {

	private Integer id;
	private String name;
}
