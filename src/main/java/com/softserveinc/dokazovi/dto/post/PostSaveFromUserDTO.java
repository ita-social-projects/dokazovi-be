package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.source.SourceDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveFromUserDTO {

	private Integer id;
	@NotNull
	private String title;
	@NotNull
	private String preview;
	@NotNull
	private String content;
	@NotNull
	private PostTypeDTO type;
	@NotNull
	@Size(min = 1, max = 3)
	private Set<DirectionDTO> directions;
	@NotNull
	@Size(min = 1, max = 3)
	private Set<TagDTO> tags;
	private Set<SourceDTO> sources;
}