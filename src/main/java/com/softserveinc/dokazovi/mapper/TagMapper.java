package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.entity.TagEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {

	TagDTO toTagDTO(TagEntity tagEntity);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "posts", ignore = true)
	TagEntity toTagEntity(TagSaveDTO tagSaveDTO);
}
