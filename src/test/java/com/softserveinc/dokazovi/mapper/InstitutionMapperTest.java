package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserInstitutionDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstitutionMapperTest {

	private final InstitutionMapper mapper = Mappers.getMapper(InstitutionMapper.class);
	private InstitutionEntity institutionEntity;
	private CityEntity cityEntity;

	@BeforeEach
	void init() {
		cityEntity = CityEntity.builder()
				.id(1)
				.name("City name")
				.build();

		institutionEntity = InstitutionEntity.builder()
				.id(1)
				.name("Some institution name")
				.city(cityEntity)
				.build();
	}

	@Test
	void toPostUserInstitutionDTO_whenMaps_thenCorrect() {
		PostUserInstitutionDTO postUserInstitutionDTO = mapper.toPostUserInstitutionDTO(institutionEntity);
		assertEquals(institutionEntity.getId(), postUserInstitutionDTO.getId());
		assertEquals(institutionEntity.getName(), postUserInstitutionDTO.getName());
	}

	@Test
	void toExpertInstitutionDTO_whenMaps_thenCorrect() {
		UserInstitutionDTO expertInstitutionDTO = mapper.toExpertInstitutionDTO(institutionEntity);

		assertEquals(expertInstitutionDTO.getId(), institutionEntity.getId());
		assertEquals(expertInstitutionDTO.getName(), institutionEntity.getName());

		assertEquals(expertInstitutionDTO.getCity().getId(), institutionEntity.getCity().getId());
		assertEquals(expertInstitutionDTO.getCity().getName(), institutionEntity.getCity().getName());
	}
}
