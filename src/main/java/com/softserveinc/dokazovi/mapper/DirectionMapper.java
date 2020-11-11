package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.UserDirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

	UserDirectionDTO toUserDirectionDTO(DirectionEntity direction);
}
