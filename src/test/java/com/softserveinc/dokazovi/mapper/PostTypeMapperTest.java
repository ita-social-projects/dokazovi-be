package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostTypeMapperTest {

	private final PostTypeMapper mapper = Mappers.getMapper(PostTypeMapper.class);
	private PostTypeEntity postTypeEntity;

	@BeforeEach
	void init() {
		postTypeEntity = PostTypeEntity.builder()
				.id(1)
				.name("Some name")
				.build();
	}

	@Test
	void toPostTypeDTO_whenMaps_thenCorrect() {
		PostTypeDTO postTypeDTO = mapper.toPostTypeDTO(postTypeEntity);
		assertEquals(postTypeEntity.getId(), postTypeDTO.getId());
		assertEquals(postTypeEntity.getName(), postTypeDTO.getName());
	}
}