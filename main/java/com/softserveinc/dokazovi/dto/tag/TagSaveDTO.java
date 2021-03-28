package com.softserveinc.dokazovi.dto.tag;

import com.softserveinc.dokazovi.annotations.TagUnique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagSaveDTO {
	@TagUnique
	@NotBlank(message = "Tag field cannot be empty")
	private String tag;
}
