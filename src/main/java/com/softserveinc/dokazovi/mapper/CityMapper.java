package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.city.CityDTO;
import com.softserveinc.dokazovi.dto.post.PostUserInstitutionCityDTO;
import com.softserveinc.dokazovi.dto.user.UserInstitutionCityDTO;
import com.softserveinc.dokazovi.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CityMapper {

    UserInstitutionCityDTO toExpertInstitutionCityDTO(CityEntity cityEntity);

    PostUserInstitutionCityDTO toPostUserInstitutionCityDTO(CityEntity cityEntity);

    @Mapping(target = "regionId", source = "cityEntity.region.id")
    CityDTO toCityDTO(CityEntity cityEntity);
}