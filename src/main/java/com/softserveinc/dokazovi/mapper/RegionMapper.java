package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.region.RegionDTO;
import com.softserveinc.dokazovi.entity.RegionEntity;
import org.mapstruct.Mapper;

/**
 * The Region Mapper is responsible for conversions
 * between the Region entity and the Region DTO.
 */

@Mapper(componentModel = "spring")
public interface RegionMapper {

    RegionDTO toRegionDTO(RegionEntity regionEntity);
}
