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

@Mapper(componentModel = "spring")
public interface UserMapper {

	PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

	PostUserDTO toPostUserDTO(UserEntity userEntity);

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
