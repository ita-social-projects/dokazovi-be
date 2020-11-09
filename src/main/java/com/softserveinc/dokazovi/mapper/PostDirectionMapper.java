package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostDirectionMapper {

	PostDirectionDTO toPostDirectionDTO(DirectionEntity directionEntity);
}
