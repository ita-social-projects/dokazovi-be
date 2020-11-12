package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserInstitutionDTO;
import com.softserveinc.dokazovi.dto.user.ExpertInstitutionDTO;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface InstitutionMapper {
	@Mapping(target = "region", source = "institution.region.name")
	PostUserInstitutionDTO toPostUserInstitutionDTO (InstitutionEntity institution);

	ExpertInstitutionDTO toExpertInstitutionDTO (InstitutionEntity institution);
}
