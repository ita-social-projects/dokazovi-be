package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostUserInstitutionDTO;
import com.softserveinc.dokazovi.dto.user.ExpertInstitutionDTO;
import com.softserveinc.dokazovi.entity.InstitutionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InstitutionMapper {

	PostUserInstitutionDTO toPostUserInstitutionDTO (InstitutionEntity institution);

	ExpertInstitutionDTO toExpertInstitutionDTO (InstitutionEntity institution);
}
