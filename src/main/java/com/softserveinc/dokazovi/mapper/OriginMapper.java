package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.origin.OriginDTO;
import com.softserveinc.dokazovi.entity.OriginEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OriginMapper {

	OriginDTO toOriginDTO(OriginEntity originEntity);
}
