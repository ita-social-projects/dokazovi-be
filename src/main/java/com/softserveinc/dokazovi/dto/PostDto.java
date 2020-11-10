package com.softserveinc.dokazovi.dto;

import com.softserveinc.dokazovi.entity.DirectionEntity;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import lombok.Data;

@Data
public class PostDto {

	private Integer id;

	private String title;

	private String content;

	private UserEntity author;

	private PostTypeEntity type;

	private DirectionEntity mainDirection;

}
