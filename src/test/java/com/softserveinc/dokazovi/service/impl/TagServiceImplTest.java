package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.entity.TagEntity;
import com.softserveinc.dokazovi.mapper.TagMapper;
import com.softserveinc.dokazovi.repositories.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

	@Mock
	private TagRepository tagRepository;
	@Mock
	private TagMapper tagMapper;
	@InjectMocks
	private TagServiceImpl tagService;

	@Test
	void save() {
		when(tagMapper.toTagEntity(any(TagSaveDTO.class))).thenReturn(new TagEntity());
		when(tagRepository.save(any(TagEntity.class))).thenReturn(new TagEntity());
		when(tagMapper.toTagDTO(any(TagEntity.class))).thenReturn(TagDTO.builder().build());

		tagService.save(new TagSaveDTO());
		verify(tagMapper, times(1)).toTagEntity(any(TagSaveDTO.class));
		verify(tagRepository, times(1)).save(any(TagEntity.class));
		verify(tagMapper, times(1)).toTagDTO(any(TagEntity.class));
	}

	@Test
	void findTagsByValue() {
		int limit = 5;
		when(tagRepository.findAll()).thenReturn(List.of(
				TagEntity.builder().id(1).tag("a").build(),
				TagEntity.builder().id(2).tag("aa").build(),
				TagEntity.builder().id(3).tag("aaa").build(),
				TagEntity.builder().id(4).tag("aaaa").build(),
				TagEntity.builder().id(5).tag("aaaaa").build(),
				TagEntity.builder().id(6).tag("aaaaaa").build(),
				TagEntity.builder().id(7).tag("aaaaaaa").build(),
				TagEntity.builder().id(8).tag("b").build(),
				TagEntity.builder().id(9).tag("bb").build(),
				TagEntity.builder().id(10).tag("bbb").build(),
				TagEntity.builder().id(11).tag("bbbb").build()
		));
		when(tagMapper.toTagDTO(any(TagEntity.class))).thenReturn(TagDTO.builder().build());
		List<TagDTO> a = tagService.findTagsByValue("a", limit);
		List<TagDTO> b = tagService.findTagsByValue("b", limit);
		assertSame(5, a.size());
		assertSame(4, b.size());
	}
}