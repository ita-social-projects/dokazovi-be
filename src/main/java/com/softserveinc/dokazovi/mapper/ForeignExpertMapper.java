package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSaveDTO;
import com.softserveinc.dokazovi.dto.foreignexpert.ForeignExpertSearchResultDTO;
import com.softserveinc.dokazovi.entity.ForeignExpertEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ForeignExpertMapper {
	ForeignExpertDTO toForeignExpertDTO(ForeignExpertEntity entity);

	ForeignExpertEntity toForeignExpertEntity(ForeignExpertSearchResultDTO saveDTO);

	ForeignExpertEntity toForeignExpertEntity(ForeignExpertSaveDTO saveDTO);

	ForeignExpertEntity updateForeignExpertEntityFromDTO(
			ForeignExpertSearchResultDTO saveDTO,
			@MappingTarget ForeignExpertEntity entity
	);

	ForeignExpertEntity updateForeignExpertEntityFromDTO(
			ForeignExpertSaveDTO saveDTO,
			@MappingTarget ForeignExpertEntity entity
	);

	ForeignExpertSearchResultDTO toForeignExpertSearchResultDTO(ForeignExpertEntity entity);
}
