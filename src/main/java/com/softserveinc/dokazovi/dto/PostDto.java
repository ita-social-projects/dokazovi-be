package com.softserveinc.dokazovi.dto;

import lombok.Data;

@Data
public class PostDto {

	private Integer id;

	private String title;

	private String content;

	private AuthorDto author;

	private PostTypeDto type;

	private DirectionDto direction;

}
