package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.city.CityDTO;
import com.softserveinc.dokazovi.dto.post.PostUserInstitutionCityDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionCityDTO;
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

	@Test
	void toExpertInstitutionCityDTO_whenMaps_thenCorrect() {
		UserInstitutionCityDTO userInstitutionCityDTO = mapper.toExpertInstitutionCityDTO(cityEntity);

		assertEquals(userInstitutionCityDTO.getId(), cityEntity.getId());
		assertEquals(userInstitutionCityDTO.getName(), cityEntity.getName());
	}

	@Test
	void toCityDTO() {
		CityDTO cityDTO = mapper.toCityDTO(cityEntity);

		assertEquals(cityDTO.getId(), cityEntity.getId());
		assertEquals(cityDTO.getName(), cityEntity.getName());
	}
}