package com.softserveinc.dokazovi.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class LatestPostDTO {

	private Integer id;
	private String title;
	private String content;
	private PostUserDTO author;
	private PostDirectionDTO direction;
	private PostTypeDTO type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp createdAt;
}
