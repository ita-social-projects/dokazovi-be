package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, DirectionMapper.class})
public interface PostMapper {

	@Mapping(target = "author", source = "author")
	@Mapping(target = "type", source = "type.name")
	@Mapping(target = "direction", source = "mainDirection")

	ImportantPostDTO toImportantPostDTO(PostEntity postEntity);
}
