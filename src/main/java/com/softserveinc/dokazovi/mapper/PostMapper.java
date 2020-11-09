package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PostUserMapper.class, PostTypeMapper.class})
public interface PostMapper {

	@Mapping(target = "postUserDTO", source = "author")
	@Mapping(target = "postTypeDTO", source = "type")
	PostDTO toPostDTO(PostEntity postEntity);
}
