package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.annotations.OriginExists;
import com.softserveinc.dokazovi.annotations.PostTypeExists;
import com.softserveinc.dokazovi.annotations.TagExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.dto.tag.TagDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostSaveFromUserDTO {

	private Integer id;

	private Integer authorId;

	private String title;

	private String videoUrl;

	private String previewImageUrl;

	@NotBlank(message = "Preview field cannot be empty")
	private String preview;

	@NotBlank(message = "Content field cannot be empty")
	private String content;

	@NotNull(message = "PostType are required")
	@PostTypeExists
	private PostTypeDTO type;

	@NotNull(message = "At least one topic are required")
	@Size(min = 1, max = 3, message = "Number of chosen topics is from 1 to 3. ")
	private Set<@DirectionExists DirectionDTO> directions;

	private Set<@TagExists TagDTO> tags;

	private Set<@OriginExists OriginDTO> origins;
}
