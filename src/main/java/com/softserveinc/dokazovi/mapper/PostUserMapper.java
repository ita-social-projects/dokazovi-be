package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.dto.post.PostUserInstitutionDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.UserInstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {PostUserInstitutionMapper.class})
public interface PostUserMapper {

	PostUserInstitutionMapper INSTITUTION_MAPPER = Mappers.getMapper(PostUserInstitutionMapper.class);

	@Mapping(target = "institution", source = "institutions", qualifiedByName = "getPrimaryUserInstitution")
	PostUserDTO toPostUserDTO(UserEntity userEntity);

	@Named("getPrimaryUserInstitution")
	default PostUserInstitutionDTO getPrimaryUserInstitution(Set<UserInstitutionEntity> institutions) {
		return institutions.stream()
				.filter(UserInstitutionEntity::isPrimary)
				.map(UserInstitutionEntity::getInstitution)
				.map(INSTITUTION_MAPPER::toPostUserInstitutionDTO)
				.findFirst()
				.orElse(null);
	}
}
