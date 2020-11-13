package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.dto.user.LatestExpertPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {InstitutionMapper.class, DirectionMapper.class, PostMapper.class})
public interface UserMapper {

	PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

	@Mapping(target = "institution", source = "mainInstitution")
	PostUserDTO toPostUserDTO(UserEntity userEntity);

	@Mapping(target = "institution", source = "mainInstitution")
	@Mapping(target = "direction", source = "mainDirection")
	@Mapping(target = "lastAddedPost", source = "posts", qualifiedByName = "getLatestPublishedPost")
	ExpertPreviewDTO toExpertPreviewDTO(UserEntity userEntity);

	@Named("getLatestPublishedPost")
	default LatestExpertPostDTO getPrimaryUserInstitution(Set<PostEntity> posts) {
		return posts.stream()
				.filter(postEntity -> postEntity.getStatus().equals(PostStatus.PUBLISHED))
				.sorted(Comparator.comparing(PostEntity::getCreatedAt))
				.map(POST_MAPPER::toLatestExpertPostDTO)
				.findFirst()
				.orElse(null);
	}
}
