package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.LatestPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostUserMapper.class, PostTypeMapper.class, PostDirectionMapper.class})
public interface PostMapper {

	@Mapping(target = "author", source = "author")
	@Mapping(target = "type", source = "type")
	@Mapping(target = "direction", source = "mainDirection")
	LatestPostDTO toLatestPostDTO(PostEntity postEntity);
}
