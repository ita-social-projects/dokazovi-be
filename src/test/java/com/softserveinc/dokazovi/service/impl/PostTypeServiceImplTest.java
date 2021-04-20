package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.entity.PostTypeEntity;
import com.softserveinc.dokazovi.mapper.PostTypeMapper;
import com.softserveinc.dokazovi.repositories.PostTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostTypeServiceImplTest {

	@Mock
	private PostTypeRepository postTypeRepository;

	@Mock
	private PostTypeMapper postTypeMapper;

	@InjectMocks
	private PostTypeServiceImpl postTypeService;

	private List<PostTypeEntity> postTypeEntities;

	@BeforeEach
	void init() {
		postTypeEntities = List.of(new PostTypeEntity(), new PostTypeEntity());
	}

	@Test
	void findAll() {
		when(postTypeRepository.findAll()).thenReturn(postTypeEntities);
		postTypeService.findAll();
		verify(postTypeMapper, times(postTypeEntities.size())).toPostTypeDTO(any(PostTypeEntity.class));
	}

	@Test
	void findAllPostTypesByUserId() {
		when(postTypeRepository.findAllPostTypesByUserId(2)).thenReturn(postTypeEntities);
		postTypeService.findAllPostTypesByUserId(2);
		verify(postTypeMapper, times(postTypeEntities.size())).toPostTypeDTO(any(PostTypeEntity.class));
	}
}