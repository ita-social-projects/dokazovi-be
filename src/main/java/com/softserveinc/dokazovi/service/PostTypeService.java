package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;

import java.util.List;

public interface PostTypeService {

    List<PostTypeDTO> findAll();

    List<PostTypeDTO> findAllPostTypesByUserId(Integer userId);

    List<PostTypeDTO> findAllPostTypesByUserIdAndStatus(Integer userId, PostStatus postStatus);
}
