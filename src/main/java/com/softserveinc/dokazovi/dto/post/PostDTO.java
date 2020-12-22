package com.softserveinc.dokazovi.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
public class PostDTO {

	private Integer id;
	private String title;
	private String content;
	private PostUserDTO author;
	private Set<DirectionDTO> directions;
	private Set<TagDTO> tags;
	private PostTypeDTO type;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp modifiedAt;
}
