package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.user.ExpertInstitutionCityDTO;
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
	void toExpertInstitutionCityDTO_whenMaps_thenCorrect() {
		ExpertInstitutionCityDTO expertInstitutionCityDTO = mapper.toExpertInstitutionCityDTO(cityEntity);

		assertEquals(expertInstitutionCityDTO.getId(), cityEntity.getId());
		assertEquals(expertInstitutionCityDTO.getName(), cityEntity.getName());
	}
}