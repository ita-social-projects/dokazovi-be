package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.PostTypeDTO;

import java.util.List;

public interface PostTypeService {

	List<PostTypeDTO> findAll();
}
