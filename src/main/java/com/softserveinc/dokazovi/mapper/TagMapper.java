package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.tag.TagDTO;
import com.softserveinc.dokazovi.dto.tag.TagSaveDTO;
import com.softserveinc.dokazovi.entity.TagEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

	TagDTO toTagDTO(TagEntity tagEntity);

	TagEntity toTagEntity(TagSaveDTO tagSaveDTO);
}
