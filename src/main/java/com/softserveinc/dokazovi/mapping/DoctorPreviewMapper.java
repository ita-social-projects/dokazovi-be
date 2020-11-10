package com.softserveinc.dokazovi.mapping;

import com.softserveinc.dokazovi.dto.DoctorPreviewDTO;
import com.softserveinc.dokazovi.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface DoctorPreviewMapper {
	DoctorPreviewDTO toDTO(UserEntity doctor);
}
