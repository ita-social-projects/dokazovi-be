package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserInstitutionDTO;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstitutionMapperTest {

	private final InstitutionMapper mapper = Mappers.getMapper(InstitutionMapper.class);
	private InstitutionEntity institutionEntity;

	@BeforeEach
	void init() {
		institutionEntity = InstitutionEntity.builder()
				.id(1)
				.name("Some institution name")
				.build();
	}

	@Test
	void toPostUserInstitutionDTO_whenMaps_thenCorrect() {
		PostUserInstitutionDTO postUserInstitutionDTO = mapper.toPostUserInstitutionDTO(institutionEntity);
		assertEquals(institutionEntity.getId(), postUserInstitutionDTO.getId());
		assertEquals(institutionEntity.getName(), postUserInstitutionDTO.getName());
	}
}