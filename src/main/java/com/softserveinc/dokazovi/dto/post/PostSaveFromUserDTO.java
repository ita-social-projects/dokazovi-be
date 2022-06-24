package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.annotations.DirectionExists;
import com.softserveinc.dokazovi.annotations.OriginExists;
import com.softserveinc.dokazovi.annotations.PostTypeExists;
import com.softserveinc.dokazovi.dto.direction.DirectionDTOForSavingPost;
import com.softserveinc.dokazovi.dto.origin.OriginDTOForSavingPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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

	@NotNull(message = "Author id is required")
	private Integer authorId;

	private String title;

	private String videoUrl;

	private String previewImageUrl;

	private String importantImageUrl;

	private String importantMobileImageUrl;

	@NotBlank(message = "Preview field cannot be empty")
	private String preview;

	@NotBlank(message = "Content field cannot be empty")
	private String content;

	@NotNull(message = "PostType are required")
	@PostTypeExists
	private PostTypeIdOnlyDTO type;

	@NotNull(message = "PostStatus are required")
	@Min(value = 0)
	@Max(value = 6)
	private Integer postStatus;

	@NotNull(message = "At least one topic are required")
	@Size(min = 1, max = 3, message = "Number of chosen topics is from 1 to 3. ")
	private Set<@DirectionExists DirectionDTOForSavingPost> directions;

	private Set<@OriginExists OriginDTOForSavingPost> origins;

	private Integer views;

	private Integer realViews;
}
