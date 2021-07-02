package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.post.PostSaveFromUserDTO;
import com.softserveinc.dokazovi.dto.user.LatestUserPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

	@Mapping(target = "author.mainInstitution", source = "author.doctor.mainInstitution")
	@Mapping(target = "author.bio", source = "author.doctor.bio")
	PostDTO toPostDTO(PostEntity postEntity);

	PostEntity toPostEntity(PostSaveFromUserDTO postSaveFromUserDTO);

	PostEntity updatePostEntityFromDTO(PostSaveFromUserDTO postSaveFromUserDTO, @MappingTarget PostEntity postEntity);

	LatestUserPostDTO toLatestExpertPostDTO(PostEntity post);
}
