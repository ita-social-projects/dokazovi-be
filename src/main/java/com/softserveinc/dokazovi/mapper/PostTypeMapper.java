package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.PostTypeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostTypeMapper {
	PostTypeDTO toPostTypeDTO(PostTypeEntity postTypeEntity);
}
