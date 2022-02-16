package com.softserveinc.dokazovi.dto.post;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

	private Integer id;
	private String title;
	private String preview;
	private String content;
	private String videoUrl;
	private String previewImageUrl;
	private PostUserDTO author;
	private Set<DirectionDTO> directions;
	private Set<TagDTO> tags;
	private PostTypeDTO type;
	private String status;
	private Set<OriginDTO> origins;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp createdAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp modifiedAt;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
	private Timestamp publishedAt;
	private Integer importanceOrder;
	private String importantImageUrl;
	private Integer views;
	private Integer realViews;
}
