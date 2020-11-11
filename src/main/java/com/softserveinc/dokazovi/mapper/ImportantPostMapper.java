package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImportantPostMapper {

	@Mapping(target = "title", source = "title")
	@Mapping(target = "author", source = "author")
	@Mapping(target = "type", source = "type")
	@Mapping(target = "direction", source = "mainDirection")
	@Mapping(target = "createdAt", source = "createdAt")
	ImportantPostDTO toImportantPostDTO(PostEntity postEntity);
}
