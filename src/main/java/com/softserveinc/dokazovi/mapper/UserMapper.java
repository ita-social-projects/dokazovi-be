package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

	PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

	PostUserDTO toPostUserDTO(UserEntity userEntity);

	@Mapping(target = "lastAddedPost",
			expression = "java(POST_MAPPER.toLatestExpertPostDTO(userEntity.getLatestExpertPost()))")
	UserDTO toUserDTO(UserEntity userEntity);
}
