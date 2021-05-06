package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.user.UserDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * The User Mapper is responsible for conversions between the user entity and the user DTO.
 */

@Mapper(componentModel = "spring")
public interface UserMapper {

	PostMapper POST_MAPPER = Mappers.getMapper(PostMapper.class);

	@Mapping(target = "id", source = "userEntity.id")
	@Mapping(target = ".", source = "userEntity.doctor")
	@Mapping(target = "lastAddedPost",
			expression = "java(POST_MAPPER.toLatestExpertPostDTO(userEntity.getLatestExpertPost()))")
	UserDTO toUserDTO(UserEntity userEntity);
}
