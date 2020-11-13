package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDirectionDTO;
import com.softserveinc.dokazovi.dto.user.UserDirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

	PostDirectionDTO toPostDirectionDTO(DirectionEntity directionEntity);

	UserDirectionDTO toUserDirectionDTO(DirectionEntity directionEntity);
}
