package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.dto.post.PostDirectionDTO;
import com.softserveinc.dokazovi.dto.user.UserDirectionDTO;
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
				.build();
	}

	@Test
	public void toPostDirectionDTO_whenMaps_thenCorrect() {
		PostDirectionDTO postDirectionDTO = mapper.toPostDirectionDTO(directionEntity);
		assertEquals(directionEntity.getId(), postDirectionDTO.getId());
		assertEquals(directionEntity.getName(), postDirectionDTO.getName());
	}

	@Test
	public void toUserDirectionDTO_whenMaps_thenCorrect() {
		UserDirectionDTO userDirectionDTO = mapper.toUserDirectionDTO(directionEntity);

		assertEquals(userDirectionDTO.getId(), directionEntity.getId());
		assertEquals(userDirectionDTO.getName(), directionEntity.getName());
	}

	@Test
	public void toDirectionDTO_whenMaps_thenCorrect() {
		DirectionDTO directionDTO = mapper.toDirectionDTO(directionEntity);

		assertEquals(directionDTO.getId(), directionEntity.getId());
		assertEquals(directionDTO.getName(), directionEntity.getName());
	}
}