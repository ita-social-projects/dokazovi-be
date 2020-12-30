package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;
import com.softserveinc.dokazovi.entity.PostTypeEntity;

import java.util.List;

public interface PostTypeService {

	List<PostTypeDTO> findAll();

	boolean exists(PostTypeEntity postTypeEntity);
}
