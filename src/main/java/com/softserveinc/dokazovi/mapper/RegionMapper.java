package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.RegionDTO;
import com.softserveinc.dokazovi.entity.RegionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {

	RegionDTO toRegionDto(RegionEntity region);
}
