package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.dto.user.ExpertPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {InstitutionMapper.class})
public interface UserMapper {

	@Mapping(target = "institution", source = "mainInstitution")
	PostUserDTO toPostUserDTO(UserEntity userEntity);

	ExpertPreviewDTO toExpertPreviewDTO(UserEntity userEntity);

}
