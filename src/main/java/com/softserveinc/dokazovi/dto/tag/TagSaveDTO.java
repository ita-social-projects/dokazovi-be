package com.softserveinc.dokazovi.dto.tag;

import com.softserveinc.dokazovi.annotations.TagUnique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagSaveDTO {
	@TagUnique
	private String tag;
}
