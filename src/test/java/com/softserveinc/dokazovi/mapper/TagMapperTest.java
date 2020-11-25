package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.entity.TagEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TagMapperTest {
	private final TagMapper tagMapper = Mappers.getMapper(TagMapper.class);
	private TagEntity tagEntity;
	private TagSaveDTO tagSaveDTO;

	@BeforeEach
	void init() {
		int tagId = 1;
		String tagValue = "someTag";

		tagEntity = TagEntity.builder()
				.id(tagId)
				.tag(tagValue)
				.build();
		tagSaveDTO = TagSaveDTO.builder()
				.tag(tagValue)
				.build();
	}

	@Test
	void toTagDTO_whenMaps_thenCorrect() {
		TagDTO tagDTO = tagMapper.toTagDTO(tagEntity);
		assertEquals(tagDTO.getId(), tagEntity.getId());
		assertEquals(tagDTO.getTag(), tagEntity.getTag());
	}

	@Test
	void toTagEntity_whenMaps_thenCorrect() {
		TagEntity tagEntity = tagMapper.toTagEntity(tagSaveDTO);
		assertEquals(tagEntity.getTag(), tagSaveDTO.getTag());
		assertNull(tagEntity.getId());
	}
}