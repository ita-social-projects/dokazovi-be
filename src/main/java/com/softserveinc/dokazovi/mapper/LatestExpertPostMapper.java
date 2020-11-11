package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.LatestExpertPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LatestExpertPostMapper {

	LatestExpertPostDTO toLatestExpertPostDTO(PostEntity post);
}
