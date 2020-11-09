package com.softserveinc.dokazovi.dto.post;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class PostDTO {
	private Integer id;
	private String title;
	private String content;
	private PostUserDTO postUserDTO;
	private PostTypeDTO postTypeDTO;
	private Timestamp createdAt;
}
