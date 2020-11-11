package com.softserveinc.dokazovi.dto.post;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class ImportantPostDTO {

	private Integer id;
	private String title;
	private PostUserDTO author;
	private PostTypeDTO type;
	private Timestamp createdAt;
	private PostDirectionDTO direction;

}
