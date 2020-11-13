package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserInstitutionCityDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CityMapperTest {

	private final CityMapper mapper = Mappers.getMapper(CityMapper.class);
	private CityEntity cityEntity;

	@BeforeEach
	void init() {
		cityEntity = CityEntity.builder()
				.id(1)
				.name("City name")
				.build();
	}

	@Test
	void toPostUserInstitutionCityDTO() {
		PostUserInstitutionCityDTO postUserInstitutionCityDTO = mapper.toPostUserInstitutionCityDTO(cityEntity);
		assertEquals(postUserInstitutionCityDTO.getId(), cityEntity.getId());
		assertEquals(postUserInstitutionCityDTO.getName(), cityEntity.getName());
	}
}