package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.city.CityDTO;
import com.softserveinc.dokazovi.dto.post.PostUserInstitutionCityDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionCityDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {

	UserInstitutionCityDTO toExpertInstitutionCityDTO(CityEntity cityEntity);

	PostUserInstitutionCityDTO toPostUserInstitutionCityDTO(CityEntity cityEntity);

	CityDTO toCityDTO(CityEntity cityEntity);
}