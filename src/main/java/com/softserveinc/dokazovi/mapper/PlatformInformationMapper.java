package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.entity.PlatformInformationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlatformInformationMapper {

	PlatformInformationDTO toPlatformInformationDTO(PlatformInformationEntity platformInformationEntity);

	PlatformInformationEntity toPlatformInformationEntity(PlatformInformationDTO platformInformationDTO);

	PlatformInformationEntity updatePlatformInformationEntity(PlatformInformationDTO platformInformationDTO,
			@MappingTarget PlatformInformationEntity platformInformationEntity);
}
