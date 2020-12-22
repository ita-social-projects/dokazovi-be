package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionMapperTest {

	private final DirectionMapper mapper = Mappers.getMapper(DirectionMapper.class);
	private DirectionEntity directionEntity;

	@BeforeEach
	void init() {
		directionEntity = DirectionEntity.builder()
				.id(1)
				.name("Some name")
				.label("Some label")
				.color("#ef5350")
				.build();
	}

	@Test
	void toDirectionDTO_whenMaps_thenCorrect() {
		DirectionDTO directionDTO = mapper.toDirectionDTO(directionEntity);

		assertEquals(directionDTO.getId(), directionEntity.getId());
		assertEquals(directionDTO.getName(), directionEntity.getName());
		assertEquals(directionDTO.getLabel(), directionEntity.getLabel());
		assertEquals(directionDTO.getColor(), directionEntity.getColor());
	}
}