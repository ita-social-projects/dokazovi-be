package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.user.ExpertRegionDTO;
import com.softserveinc.dokazovi.entity.RegionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {

	ExpertRegionDTO toRegionDto(RegionEntity region);
}
