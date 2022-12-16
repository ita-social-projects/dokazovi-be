package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.log.PostLogDTO;
import com.softserveinc.dokazovi.entity.LogEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.TARGET_IMMUTABLE)
public interface LogMapper {
    PostLogDTO toPostLogDTO(LogEntity logEntity);
}