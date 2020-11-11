package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.DoctorPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import com.softserveinc.dokazovi.entity.UserInstitutionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {InstitutionMapper.class})
public interface UserMapper {
}
