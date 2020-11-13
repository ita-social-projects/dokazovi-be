package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserDTO;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserMapperTest {

	private final UserMapper mapper = Mappers.getMapper(UserMapper.class);
	private final String secondInstitutionName = "Second institution name";
	private UserEntity userEntity;
	private InstitutionEntity mainInstitution;

	@BeforeEach
	void init() {
		mainInstitution = InstitutionEntity.builder()
				.id(1)
				.name("Some institution")
				.build();
		userEntity = UserEntity.builder()
				.id(1)
				.firstName("Some firstname")
				.lastName("Some lastname")
				.avatar("Some avatar url")
				.mainInstitution(mainInstitution)
				.build();
	}

	@Test
	void toPostUserDTO_whenMaps_thenCorrect() {
		PostUserDTO postUserDTO = mapper.toPostUserDTO(userEntity);
		assertEquals(userEntity.getId(), postUserDTO.getId());
		assertEquals(userEntity.getFirstName(), postUserDTO.getFirstName());
		assertEquals(userEntity.getLastName(), postUserDTO.getLastName());
		assertEquals(userEntity.getAvatar(), postUserDTO.getAvatar());
		assertEquals(userEntity.getMainInstitution().getId(), postUserDTO.getMainInstitution().getId());
		assertEquals(userEntity.getMainInstitution().getName(), postUserDTO.getMainInstitution().getName());
	}
}