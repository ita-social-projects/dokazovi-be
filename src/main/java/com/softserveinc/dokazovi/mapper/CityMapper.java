package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.user.ExpertInstitutionCityDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

	ExpertInstitutionCityDTO toExpertInstitutionCityDTO(CityEntity cityEntity);
}
