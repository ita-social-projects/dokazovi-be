package com.softserveinc.dokazovi.service;

import com.softserveinc.dokazovi.dto.post.ImportantPostDTO;
import com.softserveinc.dokazovi.dto.post.LatestPostDTO;
import com.softserveinc.dokazovi.entity.enumerations.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface PostService {

	Page<LatestPostDTO> findAllByStatus(PostStatus postStatus, Pageable pageable);

	Page<ImportantPostDTO> findImportantPosts(Pageable pageable);

	Page<LatestPostDTO> findAllByMainDirection(
			Integer directionId, Integer typeId, Set<Integer> tags, Pageable pageable);
}
