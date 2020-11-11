package com.softserveinc.dokazovi.dto.post;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImportantPostDTO {

	private Integer id;
	private String title;
	private PostUserDTO author;
	private PostTypeDTO type;
	private PostDirectionDTO direction;

}
