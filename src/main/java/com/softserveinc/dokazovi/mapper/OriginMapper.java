package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.entity.OriginEntity;
import org.mapstruct.Mapper;

/**
 * The Origin Mapper is responsible for conversions between the origin entity and the origin DTO.
 */
@Mapper(componentModel = "spring")
public interface OriginMapper {

    OriginDTO toOriginDTO(OriginEntity originEntity);
}
