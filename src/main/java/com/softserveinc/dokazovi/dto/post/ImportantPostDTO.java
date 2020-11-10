package com.softserveinc.dokazovi.dto.post;

import com.softserveinc.dokazovi.dto.AuthorDTO;
import com.softserveinc.dokazovi.dto.PostTypeDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImportantPostDTO {

	private Integer id;

	private String title;

	private AuthorDTO author;

	private PostTypeDTO type;

	private PostDirectionDTO direction;

}
