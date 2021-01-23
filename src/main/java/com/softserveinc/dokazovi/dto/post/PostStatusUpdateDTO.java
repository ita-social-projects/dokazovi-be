package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.annotations.PostExistsById;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostStatusUpdateDTO {

	@PostExistsById
	@NotNull(message = "Post id is required")
	private Integer postId;

	@NotNull(message = "PostStatus is required")
	private PostStatus postStatus;

}
