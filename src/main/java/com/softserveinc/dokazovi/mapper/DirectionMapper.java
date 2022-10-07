package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.direction.DirectionDTO;
import com.softserveinc.dokazovi.entity.DirectionEntity;
import org.mapstruct.Mapper;

/**
 * The Direction Mapper is responsible for conversions
 * between the Direction entity and the Direction DTO.
 */

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    DirectionDTO toDirectionDTO(DirectionEntity directionEntity);
}
