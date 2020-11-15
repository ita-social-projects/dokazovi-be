package com.softserveinc.dokazovi.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp createdAt;
	private PostDirectionDTO direction;

}
