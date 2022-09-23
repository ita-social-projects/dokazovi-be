package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.info.PlatformInformationDTO;
import com.softserveinc.dokazovi.entity.PlatformInformationEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface PlatformInformationMapper {

    PlatformInformationDTO toPlatformInformationDTO(PlatformInformationEntity platformInformationEntity);

    PlatformInformationEntity toPlatformInformationEntity(PlatformInformationDTO platformInformationDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    PlatformInformationEntity updatePlatformInformationEntity(PlatformInformationDTO platformInformationDTO,
            @MappingTarget PlatformInformationEntity platformInformationEntity);
}
