package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;

import java.util.List;

public interface TagService {

	TagDTO save(TagSaveDTO tagSaveDTO);

	List<TagDTO> findTagsByValue(String value, Integer limit);
}
