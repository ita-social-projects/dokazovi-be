package com.softserveinc.dokazovi.mapper;

import com.softserveinc.dokazovi.dto.post.PostDTO;
import com.softserveinc.dokazovi.dto.user.LatestExpertPostDTO;
import com.softserveinc.dokazovi.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostDTO toPostDTO(PostEntity postEntity);

	LatestExpertPostDTO toLatestExpertPostDTO(PostEntity post);
}
