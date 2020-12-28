package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.user.LatestUserPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostDTO toPostDTO(PostEntity postEntity);

	@Mapping(target = "important", expression = "java(false)")
	PostEntity toPostEntity(PostSaveFromUserDTO postSaveFromUserDTO);

	LatestUserPostDTO toLatestExpertPostDTO(PostEntity post);

	void updatePostEntityFromDTO(PostSaveFromUserDTO postSaveFromUserDTO, @MappingTarget PostEntity postEntity);
}
