package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.InstitutionDTO;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface InstitutionMapper {

	@Mapping(target = "region", source = "institution.region")
	InstitutionDTO toInstitutionDTO(InstitutionEntity institution);
}
