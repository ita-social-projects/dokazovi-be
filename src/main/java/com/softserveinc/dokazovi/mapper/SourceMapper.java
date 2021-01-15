package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.source.SourceDTO;
import com.softserveinc.dokazovi.entity.SourceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SourceMapper {
	SourceDTO toSourceDTO(SourceEntity sourceEntity);
}
