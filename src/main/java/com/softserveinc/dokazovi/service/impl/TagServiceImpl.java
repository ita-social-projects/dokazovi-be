package com.softserveinc.dokazovi.service.impl;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.entity.TagEntity;
import com.softserveinc.dokazovi.mapper.TagMapper;
import com.softserveinc.dokazovi.repositories.TagRepository;
import com.softserveinc.dokazovi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

	private final TagRepository tagRepository;
	private final TagMapper tagMapper;

	@CacheEvict(value = "tags", allEntries = true)
	public TagDTO save(TagSaveDTO tagSaveDTO) {
		TagEntity tagEntity = tagRepository.save(tagMapper.toTagEntity(tagSaveDTO));
		return tagMapper.toTagDTO(tagEntity);
	}

	public List<TagDTO> findTagsByValue(String value, Integer limit) {
		String filter = value.toLowerCase();
		return tagRepository.findAll()
				.stream()
				.filter(tagDTO -> tagDTO.getTag().toLowerCase().contains(filter))
				.limit(limit)
				.map(tagMapper::toTagDTO)
				.collect(Collectors.toList());
	}
}
